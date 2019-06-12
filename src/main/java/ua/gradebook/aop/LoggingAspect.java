package ua.gradebook.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;

import javax.servlet.http.HttpSession;

public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    public void addMethodBeforeAll(JoinPoint joinPoint) {
        logger.debug("***LoggingAspect.addMethodBeforeAll***" + joinPoint.getSignature().getName());
    }
}
