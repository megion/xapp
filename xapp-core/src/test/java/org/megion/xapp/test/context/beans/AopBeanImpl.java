package org.megion.xapp.test.context.beans;

import org.megion.xapp.core.aop.annotation.CheckTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AopBeanImpl implements AopBean {

	@Override
	@CheckTransaction(required=true)
	public void requiredTransaction() {
		System.out.println("call requiredTransaction");
	}

    @Override
    @Transactional
    @CheckTransaction(required=false)
    public void withoutTransaction() {
        System.out.println("call withoutTransaction");
    }


}
