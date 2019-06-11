package ua.gradebook.aop;

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
    @Autowired
    @Qualifier("PersonService")
    AppServiceExtension personService;

    public void addMethodBeforeAll(JoinPoint joinPoint) {
        System.out.println("***LoggingAspect.addMethodBeforeAll***" + joinPoint.getSignature().getName());
    }

    public void addLoggedPersonToSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = (Person) personService.findByLogin(user.getUsername());
        session.setAttribute("loggedPerson", person);
    }
}
