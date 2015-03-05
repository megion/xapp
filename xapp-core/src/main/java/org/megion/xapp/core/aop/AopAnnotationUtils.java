package org.megion.xapp.core.aop;

import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by izadorozhny on 05.03.2015.
 */
public class AopAnnotationUtils {

    public static <A extends Annotation> A findAnnotation(Method method, Class<?> targetClass, Class<A> annotationType) {
        // Ignore CGLIB subclasses - introspect the actual user class.
        Class<?> userClass = ClassUtils.getUserClass(targetClass);

        // The method may be on an interface, but we need attributes from the
        // target class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method,
                userClass);

        // First try is the method in the target class.
        A ann = org.springframework.core.annotation.AnnotationUtils.getAnnotation(specificMethod, annotationType);
        if (ann != null) {
            return ann;
        }

        if (specificMethod != method) {
            // Fallback is to look at the original method.
            ann = org.springframework.core.annotation.AnnotationUtils.getAnnotation(method, annotationType);
            if (ann != null) {
                return ann;
            }
        }
        return null;
    }
}
