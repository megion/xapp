package org.megion.xapp.test.aop.annotation;

public @interface CheckTransaction {

	boolean required() default false;
}
