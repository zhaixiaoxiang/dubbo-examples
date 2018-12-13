package com.zxx.dubbo.hessiannpedemoserver;

import com.zxx.dubbo.hessiannpedemoapi.service.GreetingService;

public class GreetingServiceImpl implements GreetingService {
    public String sayHello() throws Exception {
        while (true) {
            try {
                ((Object) null).getClass();
            }
            catch (Exception e) {
                if (e.getStackTrace().length == 0) {
                    System.out.println("##########yeah!!!!!!!!");
                    throw e;
                }
            }
        }
    }
}
