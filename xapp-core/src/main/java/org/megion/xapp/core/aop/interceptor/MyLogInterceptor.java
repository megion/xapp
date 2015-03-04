package org.megion.xapp.core.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyLogInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("LogInterceptor Class : "
				+ invocation.getClass().getCanonicalName());
		System.out.println("LogInterceptor Method name : "
				+ invocation.getMethod().getName());
		Object rsp = invocation.proceed();
		return rsp;
	}

}
