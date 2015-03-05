package org.megion.xapp.core.service;

import org.megion.xapp.core.aop.annotation.CheckTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AopBeanImpl implements AopBean {

	@Override
	@Transactional
	@CheckTransaction(required=true)
	public void requiredTransaction() {
		System.out.println("call requiredTransaction");
	}


}
