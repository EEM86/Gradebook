package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    /**
     * Main page with saved logged Person object in the session.
     * @param session current client-server session
     * @param model current model
     * @return name of a jsp page
     */
    @GetMapping(value="/")
    public String Main (HttpSession session, Model model) {
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
