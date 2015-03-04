package org.megion.xapp.core.aop;

import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class AnnotationRegistry implements BeanFactoryPostProcessor {

	private Class<?> annotationType;
	private Class<?> interceptorBeanClass;
	private Integer order;

	public void setAnnotationType(Class<?> annotationType) {
		this.annotationType = annotationType;
	}

	public void setInterceptorBeanClass(Class<?> interceptorBeanClass) {
		this.interceptorBeanClass = interceptorBeanClass;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinitionRegistry registry = ((BeanDefinitionRegistry) beanFactory);

		// Ensure an auto-proxy creator is registered.
		AopConfigUtils.registerAutoProxyCreatorIfNecessary(registry);

		BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

		// Create interceptor
		RootBeanDefinition interceptorDef = new RootBeanDefinition(
				interceptorBeanClass);
		interceptorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		String interceptorName = beanNameGenerator.generateBeanName(
				interceptorDef, registry);
		registry.registerBeanDefinition(interceptorName, interceptorDef);

		// Create advisor
		RootBeanDefinition advisorDef = new RootBeanDefinition(
				AnnotatedMethodPointcutAdvisor.class);
		advisorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		advisorDef.getPropertyValues().add("adviceBeanName", interceptorName);
		advisorDef.getPropertyValues().add("annotationType", annotationType);
		if (order!=null) {
			advisorDef.getPropertyValues().add("order", order);
		}
		String advisorName = beanNameGenerator.generateBeanName(
				advisorDef, registry);
		registry.registerBeanDefinition(advisorName, advisorDef);
	}

}
