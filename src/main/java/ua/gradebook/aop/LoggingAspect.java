package ua.gradebook.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    public void addMethodBeforeAll(JoinPoint joinPoint) {
        logger.debug("***LoggingAspect.addMethodBeforeAll***" + joinPoint.getSignature().getName());
    }
}
