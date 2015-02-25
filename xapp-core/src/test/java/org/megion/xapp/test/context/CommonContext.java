package org.megion.xapp.test.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:common.xml"})
public class CommonContext {
}
