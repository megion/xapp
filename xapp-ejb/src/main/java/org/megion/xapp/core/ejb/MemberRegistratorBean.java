package org.megion.xapp.core.ejb;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.megion.xapp.core.entity.Member;
import org.megion.xapp.core.service.MemberRegistrator;
import org.megion.xapp.core.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)	
@Singleton
@Local(MemberRegistrator.class)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class MemberRegistratorBean implements MemberRegistrator {
	
	@Autowired
	private MemberService memberService; 

	@Lock(LockType.WRITE)
	@Override
	public void register(Member member) {
		memberService.register(member);
	}

}
