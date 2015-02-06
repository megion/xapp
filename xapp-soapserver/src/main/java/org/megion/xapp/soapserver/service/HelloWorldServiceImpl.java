package org.megion.xapp.soapserver.service;

import javax.jws.WebService;

@WebService(endpointInterface = "org.megion.xapp.soapserver.service.HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}
