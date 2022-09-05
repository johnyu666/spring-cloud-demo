package cn.johnyu.order.controller;

import cn.johnyu.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/orders")
    public String findOrder(){
        String info = restTemplate.getForObject("http://payment-service:3000/payments/1", String.class);
        return  "订单调用： "+info;
    }

    @RequestMapping(value = "/orders1")
    public String findOrder1(){
        String info=paymentService.findPayment();
        return  "订单调用： "+info;
    }
}
