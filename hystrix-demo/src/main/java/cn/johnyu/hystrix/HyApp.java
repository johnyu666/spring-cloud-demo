package cn.johnyu.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class HyApp {
    public static void main(String[] args) {
        final MyService myService=new MyService();
        //打开10个线程（生产环境下，此线程由tomcat线程池分配）
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    MyCommand reject=new MyCommand(myService);
                    String rs=reject.execute();
                    System.out.println(Thread.currentThread().getName()+":"+rs);
                }
            }.start();

        }

    }
}
//模拟实际业务类，其工作会有延迟
class MyService{
    public String work(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return "工作结果："+Math.random()+"："+Thread.currentThread().getName();
    }
}
//自定义的Command
class MyCommand extends HystrixCommand<String> {
    private String result;
    private MyService service;
    public MyCommand(MyService service) {//业务对象传入到
        //设定线程池的名字
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Default"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()//线程池属性设定器
                                .withCoreSize(5)// 设置线程池大小
                                .withMaxQueueSize(2))// 设置最大等待队列大小,最终会有七个请求执行
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()//命令属性设定器
                        .withExecutionTimeoutInMilliseconds(3000)));// 设置timeout时长，默认1000
        this.service=service;
    }
    @Override
    protected String run() throws Exception {
        //在线程池中的执行业务，如果超时，则进入降级
        result = service.work();
        return result;
    }
    @Override
    protected String getFallback() {
        return "降级处理后的结果";
    }
}
