package cn.johnyu.payment.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class ServiceUtil {
    public String getServiceId(){

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return  "THREAD : "+Thread.currentThread().getName()+"   PROCESS : "+runtimeMXBean.getName();
    }
}
