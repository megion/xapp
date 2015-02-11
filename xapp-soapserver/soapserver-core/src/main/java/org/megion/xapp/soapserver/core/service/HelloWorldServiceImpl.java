package org.megion.xapp.soapserver.core.service;

import javax.jws.WebService;

@WebService(endpointInterface = "org.megion.xapp.soapserver.core.service.HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}
