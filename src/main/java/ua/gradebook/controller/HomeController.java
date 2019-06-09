package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("loggedPerson")
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    PersonService personService;

    /**
     * Main page with saved logged Person object in the session.
     * @param session current client-server session
     * @param model current model
     * @return name of a jsp page
     */
    @GetMapping(value="/")
    public String Main (HttpSession session, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = (Person) personService.findByLogin(user.getUsername());
        session.setAttribute("loggedPerson", person);
        logger.info("index load");
        return "index";
    }

    @RequestMapping(value = "persons-rest",  method = {RequestMethod.GET})
    public String restPage() {
        return "persons-rest";
    }

    @GetMapping(value = "roles-rest")
    public String rolesRestPage() {
        return "roles-rest";
    }

    @GetMapping(value = "disciplines-rest")
    public String disciplinesRestPage() {
        return "disciplines-rest";
    }

    @GetMapping(value = "containers-rest")
    public String containersRestPage() {
        return "containers-rest";
    }

    @GetMapping(value = "branchtypes-rest")
    public String branchTypesRestPage() {
        return "branchtypes-rest";
    }

    @GetMapping(value = "journal-rest")
    public String journalRestPage() {
        return "journal-rest";
    }

    @GetMapping(value = "lessonsplan-rest")
    public String lessonsPlanRestPage() {
        return "lessonsplan-rest";
    }

    @GetMapping(value = "message-rest")
    public String messageRestPage() {
        return "message-rest";
    }



}
