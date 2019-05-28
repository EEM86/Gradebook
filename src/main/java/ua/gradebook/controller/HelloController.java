package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.gradebook.model.beans.Roles;
import ua.gradebook.model.dao.DAOImpl;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private DAOImpl dao;

    private List<Roles> rolesList;
    private static final Logger logger = Logger.getLogger(HelloController.class);

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        rolesList = dao.getRoles();
        logger.debug("rolesList1");
        logger.error("er");
        String message = "<br><div style='text-align:center;'>"
                + "<h3>Hello World, this is Gradebook Spring MVC</h3></div><br><br>";
        return new ModelAndView("welcome", "message", message);
    }
}