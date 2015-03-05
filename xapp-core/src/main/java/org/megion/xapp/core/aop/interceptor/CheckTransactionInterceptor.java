package org.megion.xapp.core.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.megion.xapp.core.aop.AopAnnotationUtils;
import org.megion.xapp.core.aop.annotation.CheckTransaction;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class CheckTransactionInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("CheckTransactionInterceptor method "
				+ invocation.getMethod());

        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);

        CheckTransaction checkAnnotation = AopAnnotationUtils.findAnnotation(invocation.getMethod(), targetClass, CheckTransaction.class);

		boolean transactionFound = TransactionSynchronizationManager
				.isActualTransactionActive();

		if (checkAnnotation.required()) {
			if (!transactionFound) {
				throw new NoTransactionException("Transaction not found");
			}
		} else {
			if (transactionFound) {
				throw new YesTransactionException("Transaction found");
			}
		}

		Object rsp = invocation.proceed();
		return rsp;
	}

}
