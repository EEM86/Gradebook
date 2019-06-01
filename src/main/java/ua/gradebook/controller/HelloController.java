package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.beans.Role;
import ua.gradebook.model.dao.PersonDAOImpl;
import ua.gradebook.model.dao.RoleDAOImpl;
import ua.gradebook.service.PersonService;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private PersonService personService;

    private List<ParentBean> rolesList;
    private static final Logger logger = Logger.getLogger(HelloController.class);

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        String message = "<br><div style='text-align:center;'>"
                + "<h3>Hello World, this is Gradebook Spring MVC</h3></div><br><br>";
        return new ModelAndView("welcome", "message", message);
    }

    @RequestMapping(value = "persons-rest",  method = {RequestMethod.GET})
    public String restPage() {

        return "persons-rest";
    }

//    @GetMapping(value="welcome")
//    public String getPersons(Model model) {
//        Role role = new Role(5, "teacher1");
//        System.out.println("next - " );
//        rolesList = dao.findAll();
//        logger.debug(rolesList.toString());
//        logger.error("test logging");
//        model.addAttribute("person", new Person());
//        //model.addAttribute("getPersons", dao.getPerson());
//        return "welcome";
//    }

}