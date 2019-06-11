package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Container;
import ua.gradebook.service.AppService;

@Controller
public class ContainerController {
    @Autowired
    @Qualifier("ContainerService")
    private AppService containerService;

    private static final Logger logger = Logger.getLogger(ContainerController.class);

    @GetMapping(value = "containers")
    public String showAllContainer(Model model) {
        model.addAttribute("container", new Container());
        model.addAttribute("getContainers", containerService.findAll());
        logger.info("containers load");
        return "containers";
    }

    @PostMapping(value = "/containers/add")
    public String addContainer(@ModelAttribute("container") Container container){
        if (container.getId() == null) {
            this.containerService.insert(container);
        } else {
            this.containerService.update(container);
        }
        return "redirect:/containers";
    }

    @RequestMapping(value = "/containers/delete/{id}")
    public String deleteContainer(@PathVariable("id") int id){
        this.containerService.delete(id);
        return "redirect:/containers";
    }

    @RequestMapping(value = "/containers/edit/{id}")
    public String editContainer(@PathVariable("id") int id, Model model){
        model.addAttribute("container", this.containerService.findById(id));
        model.addAttribute("getContainers", this.containerService.findAll());
        return "containers";
    }
}
