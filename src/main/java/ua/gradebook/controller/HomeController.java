package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @GetMapping(value="/")
    public String Main (Model model) {
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

}
