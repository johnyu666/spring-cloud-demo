package cn.johnyu.payment;

import cn.johnyu.payment.util.ServiceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class PaymentApp {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApp.class,args);

//        System.out.println(getProcessAndThread());
    }
    @Bean
    public ServiceUtil serviceId(){
        return new ServiceUtil();
    }
}

