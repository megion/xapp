package org.megion.xapp.test.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.megion.xapp.test.aop.annotation.CheckTransaction;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class CheckTransactionInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("CheckTransactionInterceptor method "
				+ invocation.getMethod());

		CheckTransaction checkAnnotation = AnnotationUtils.findAnnotation(
				invocation.getMethod(), CheckTransaction.class);

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
