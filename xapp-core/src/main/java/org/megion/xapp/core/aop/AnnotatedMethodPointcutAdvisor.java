package org.megion.xapp.core.aop;

import java.lang.annotation.Annotation;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;

public class AnnotatedMethodPointcutAdvisor<A extends Annotation> extends AbstractBeanFactoryPointcutAdvisor implements InitializingBean {

	private static final long serialVersionUID = -1516645094552659569L;
	
	private Pointcut pointcut;
	
	private Class<A> annotationType;

	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}	

	@Override
	public void afterPropertiesSet() throws Exception {
		pointcut = new AnnotatedMethodPointcut<A>(annotationType);
	}

	public Class<?> getAnnotationType() {
		return annotationType;
	}

	public void setAnnotationType(Class<A> annotationType) {
		this.annotationType = annotationType;
	}

}
