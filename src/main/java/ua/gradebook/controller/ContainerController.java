package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.service.PersonService;

import java.util.List;

@Controller
public class ContainerController {

    @Autowired
    private PersonService personService;

    private List<ParentBean> containerList;
    private static final Logger logger = Logger.getLogger(ContainerController.class);

    @RequestMapping(value = "/container", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        String message = "<br><div style='text-align:center;'>"
                + "<h3>Hello World, this is Gradebook Spring MVC</h3></div><br><br>";
        return new ModelAndView("welcome", "message", message);
    }
}
