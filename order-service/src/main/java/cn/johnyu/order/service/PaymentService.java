package cn.johnyu.order.service;

import cn.johnyu.order.service.impl.PaymentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "payment-service",fallback = PaymentServiceFallback.class)
public interface PaymentService {
    @RequestMapping("/payments")
    String findPayment();
}
