package ua.gradebook.aop;

import org.aspectj.lang.JoinPoint;

public class LoggingAspect {

    public void addMethodBeforeAll(JoinPoint joinPoint) {
        System.out.println("***LoggingAspect.addMethodBeforeAll***" + joinPoint.getSignature().getName());
    }
}
