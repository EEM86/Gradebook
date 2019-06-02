package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @GetMapping(value="/")
    public String Main (Model model) {
        logger.info("index load");
        return "index";
    }

}
