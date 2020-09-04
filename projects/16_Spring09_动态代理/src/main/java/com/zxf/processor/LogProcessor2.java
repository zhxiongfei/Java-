package com.zxf.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LogProcessor2 implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object target, String beanName) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(getClass().getClassLoader());
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new LogMethodInterceptor(target));
        return enhancer.create();
    }

    private static class LogMethodInterceptor implements MethodInterceptor{

        private final Object target;

        public LogMethodInterceptor(Object target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

            System.out.println("1--------------");
            Object res = method.invoke(target, args);
            System.out.println("1--------------");
            return res;
        }
    }
}
