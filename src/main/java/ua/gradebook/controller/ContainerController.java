package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.BranchType;
import ua.gradebook.model.beans.Container;
import ua.gradebook.service.AppService;
import ua.gradebook.service.AppServicePerson;
import ua.gradebook.service.ContainerService;

@Controller
public class ContainerController {
    private final AppService<Container> containerService;
    private final AppService<BranchType> branchTypeAppService;
    private final AppServicePerson personService;

    private static final Logger logger = Logger.getLogger(ContainerController.class);

    @Autowired
    public ContainerController(ContainerService containerService, AppService<BranchType> branchTypeAppService, AppServicePerson personService) {
        this.containerService = containerService;
        this.branchTypeAppService = branchTypeAppService;
        this.personService = personService;
    }

    @GetMapping(value = "containers")
    public String showAllContainer(Model model) {
        model.addAttribute("container", new Container());
        model.addAttribute("getBranchTypes", branchTypeAppService.findAll());
        model.addAttribute("getContainers", containerService.findAll());
        model.addAttribute("getChiefs", personService.findAll());
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
        model.addAttribute("getBranchTypes", branchTypeAppService.findAll());
        model.addAttribute("getContainers", this.containerService.findAll());
        model.addAttribute("getChiefs", personService.findAll());
        return "containers";
    }
}
