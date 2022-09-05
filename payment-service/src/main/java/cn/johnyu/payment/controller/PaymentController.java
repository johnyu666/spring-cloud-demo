package cn.johnyu.payment.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

@RestController

public class PaymentController {
//    @HystrixCommand(fallbackMethod = "fallbackHandler",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
    @HystrixCommand(
            fallbackMethod = "fallbackHandler",
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize",value = "2"),
//                    @HystrixProperty(name = "maxQueueSize",value = "1")
//            },
            commandProperties={
                @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"),
                @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "2"),
                @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "40"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "8000")})
    @RequestMapping("/payments/{id}")
    public String findPayment(@PathVariable int id){
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {}
        int x=1/id;
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String info="THREAD : "+Thread.currentThread().getName()+"   PROCESS : "+runtimeMXBean.getName();
        return info;
    }
    public String fallbackHandler(int id){
        return "降级处理";
    }
}
