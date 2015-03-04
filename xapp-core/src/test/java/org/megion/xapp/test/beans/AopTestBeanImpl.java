package org.megion.xapp.test.beans;

import org.megion.xapp.test.aop.annotation.CheckTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AopTestBeanImpl implements AopTestBean {

	@Override
	@Transactional
	@CheckTransaction(required=true)
	public void requiredTransaction() {
	}


}
