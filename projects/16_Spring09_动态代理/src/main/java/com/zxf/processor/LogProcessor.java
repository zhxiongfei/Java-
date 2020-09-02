package com.zxf.processor;

import com.zxf.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 会拦截每个bean的生命周期
 *
 * */
public class LogProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object target, String beanName) throws BeansException {
        return target;

//        Proxy proxyObj = (Proxy) Proxy.newProxyInstance(getClass().getClassLoader(),  // classloader
//                target.getClass().getInterfaces(),                              // 代理类需要实现的接口
//                new LogInvocationHandler(target));
//        return proxyObj;
    }

    private static class LogInvocationHandler implements InvocationHandler {

        private final Object target;
        public LogInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("proxy --- 1");
            Object res = method.invoke(target, args);
            System.out.println("proxy --- 2");
            return res;
        }
    }
}
