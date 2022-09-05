package cn.johnyu.order.service.impl;

import cn.johnyu.order.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFallback implements PaymentService {

    @Override
    public String findPayment() {
        return "失败了";
    }
}
