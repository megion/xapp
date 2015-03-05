package org.megion.xapp.core.aop;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

public class AnnotatedMethodPointcut<A extends Annotation> implements Pointcut, Serializable {

	private static final long serialVersionUID = 4533447839260186274L;

	private final Class<A> annotationType;

	final Set<CacheKey> cache = new CopyOnWriteArraySet<CacheKey>();

	public Class<A> getAnnotationType() {
		return annotationType;
	}

	public AnnotatedMethodPointcut(Class<A> annotationType) {
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
				CacheKey key = new CacheKey(method, targetClass==null?null:targetClass.getName());
				if (cache.contains(key)) {
					System.out.println("########## contains targetClass "
							+ targetClass.getCanonicalName() + " method: " + method + " targetClass " + targetClass);
					return true;
				} else {
                    A ann = AopAnnotationUtils.findAnnotation(method, targetClass, annotationType);
					if (ann==null) {
                        return false;
                    }
					System.out.println("++++++++++ add method: " + method + " targetClass " + targetClass);
                    cache.add(key);
					return true;
				}
			}

			@Override
			public boolean isRuntime() {
				return false;
			}
		};
	}

	private static class CacheKey {
		private final Method method;
        private final String className;

        private CacheKey(Method method, String className) {
            this.method = method;
            this.className = className;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheKey cacheKey = (CacheKey) o;

            if (className != null ? !className.equals(cacheKey.className) : cacheKey.className != null) return false;
            if (method != null ? !method.equals(cacheKey.method) : cacheKey.method != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = method != null ? method.hashCode() : 0;
            result = 31 * result + (className != null ? className.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "CacheKey{" +
                    "method=" + method +
                    ", className='" + className + '\'' +
                    '}';
        }
    }

}
