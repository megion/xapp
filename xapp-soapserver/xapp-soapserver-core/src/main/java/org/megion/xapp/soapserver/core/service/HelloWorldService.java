package org.megion.xapp.soapserver.core.service;

import javax.jws.WebService;

@WebService
public interface HelloWorldService {
    String sayHi(String text);
}
