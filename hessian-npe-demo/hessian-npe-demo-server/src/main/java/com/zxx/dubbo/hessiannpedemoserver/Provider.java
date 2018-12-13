package com.zxx.dubbo.hessiannpedemoserver;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.zxx.dubbo.hessiannpedemoapi.service.GreetingService;

import java.io.IOException;

public class Provider {

    /**
     * run this in run mode with -Djava.net.preferIPv4Stack=true
     * otherwise we can't recur the jvm OmitStackTraceInFastThrow optimization
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<GreetingService>();
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());
        serviceConfig.export();
        System.in.read();
    }
}
