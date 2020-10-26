package com.zxf.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("LogInterceptor -----  1");

        // 调用目标对象的目标方法
        Object res = methodInvocation.proceed();

        System.out.println("LogInterceptor -----  2");

        return res;

    }

}
