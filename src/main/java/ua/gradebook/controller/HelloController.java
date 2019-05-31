package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.beans.Role;
import ua.gradebook.model.dao.RoleDAOImpl;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private RoleDAOImpl dao;

    private List<ParentBean> rolesList;
    private static final Logger logger = Logger.getLogger(HelloController.class);

/*    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        rolesList = dao.getRoles();
        logger.debug("rolesList1");
        logger.error("test logging");
        String message = "<br><div style='text-align:center;'>"
                + "<h3>Hello World, this is Gradebook Spring MVC</h3></div><br><br>";
        return new ModelAndView("welcome", "message", message);
 }*/

    @GetMapping(value="welcome")
    public String getPersons(Model model) {
        Role role = new Role(5, "teacher1");
        System.out.println("next - " );
        rolesList = dao.findAll();
        logger.debug(rolesList.toString());
        logger.error("test logging");
        model.addAttribute("person", new Person());
        //model.addAttribute("getPersons", dao.getPerson());
        return "welcome";
    }
}