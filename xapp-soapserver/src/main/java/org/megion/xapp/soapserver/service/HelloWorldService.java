package org.megion.xapp.soapserver.service;

import javax.jws.WebService;

@WebService
public interface HelloWorldService {
    String sayHi(String text);
}
