package org.megion.xapp.core.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.megion.xapp.core.aop.annotation.MyLog;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

public class AnnotatedMethodPointcut implements Pointcut, Serializable {

	private static final long serialVersionUID = 4533447839260186274L;

	private final Class<?> annotationType;

	final Set<CacheKey> cache = new CopyOnWriteArraySet<CacheKey>();

	public Class<?> getAnnotationType() {
		return annotationType;
	}

	public AnnotatedMethodPointcut(Class<?> annotationType) {
		this.annotationType = annotationType;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter() {
			@Override
			public boolean matches(Class<?> clazz) {
				return true;
			}
		};
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return new MethodMatcher() {

			@Override
			public boolean matches(Method method, Class<?> targetClass,
					Object[] args) {
				// should never be invoked because isRuntime() returns false
				throw new UnsupportedOperationException(
						"Illegal MethodMatcher usage");
			}

			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				//CacheKey key = new CacheKey(method);
				/*if (cache.contains(key)) {
					System.out.println("########## contains targetClass "
							+ targetClass.getCanonicalName() + " method: " + method);
					return true;
				} else {*/
					boolean b = containsAnnotation(method, targetClass);
					if (!b) {
						return false;
					}
					System.out.println("++++++++++ add method: " + method);
					return true;
				//}
			}

			@Override
			public boolean isRuntime() {
				return false;
			}
		};
	}
	
	private CacheKey addKey(Method method) {
		CacheKey key = new CacheKey(method);
		cache.add(key);
		return key;
	}

	private boolean containsAnnotation(Method method, Class<?> targetClass) {
		// Ignore CGLIB subclasses - introspect the actual user class.
		Class<?> userClass = ClassUtils.getUserClass(targetClass);

		// The method may be on an interface, but we need attributes from the
		// target class.
		// If the target class is null, the method will be unchanged.
		Method specificMethod = ClassUtils.getMostSpecificMethod(method,
				userClass);

		// First try is the method in the target class.
		MyLog ml = AnnotationUtils.getAnnotation(specificMethod, MyLog.class);
		if (ml != null) {
			addKey(specificMethod);
			addKey(method);
			return true;
		}

		if (specificMethod != method) {
			// Fallback is to look at the original method.
			ml = AnnotationUtils.getAnnotation(method, MyLog.class);
			if (ml != null) {
				addKey(method);
				return true;
			}
		}
		return false;
	}

	private static class CacheKey {
		private final Method method;

		public CacheKey(Method method) {
			this.method = method;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((method == null) ? 0 : method.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CacheKey other = (CacheKey) obj;
			if (method == null) {
				if (other.method != null)
					return false;
			} else if (!method.equals(other.method))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "CacheKey [method=" + method + "]";
		}
	}

}
