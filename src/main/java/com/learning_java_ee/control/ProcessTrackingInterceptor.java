package com.learning_java_ee.control;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.function.Function;

@Interceptor
@Tracked(ProcessTracker.Category.UNUSED)
public class ProcessTrackingInterceptor {

    @Inject
    ProcessTracker processTracker;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        Tracked tracked = retrieveAnnotationContext(context);
        processTracker.track(tracked.value());
        return context.proceed();
    }

    private Tracked retrieveAnnotationContext(InvocationContext context) {
        Method method = context.getMethod();
        Function<AnnotatedElement, Tracked> extractor = c -> c.getAnnotation(Tracked.class);
        Tracked tracked = extractor.apply(method);
        return tracked != null ? tracked : extractor.apply(method.getDeclaringClass());
    }
}
