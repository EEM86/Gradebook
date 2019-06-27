package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.BranchType;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.beans.Role;
import ua.gradebook.service.AppService;
import ua.gradebook.service.AppServiceContainer;
import ua.gradebook.service.AppServicePerson;
import ua.gradebook.service.ContainerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ContainerController {
    private final AppServiceContainer<Container> containerService;
    private final AppService<BranchType> branchTypeAppService;
    private final AppServicePerson<Person> personService;

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

    /**
     * Finds root container of a group or department (from student or teacher)
     * @param request current request from a logged in user
     * @param model with root container for containers.jsp page
     * @return person root container of group if person is a student and of department if a person is a teacher
     */
    @GetMapping(value = "root-container")
    public String showRootContainer(HttpServletRequest request, Model model) {
        Person loggedPerson = this.personService.findByLogin(request.getUserPrincipal().getName());
        int containerId = loggedPerson.getRole().getId() == Role.STUDENT_ID ? loggedPerson.getGroup().getId() : loggedPerson.getDepartment().getId();
        model.addAttribute("container", new Container());
        model.addAttribute("getRootContainer", containerService.findRootContainer(containerId));
//        model.addAttribute("getDepartments", containerService.findDepartments());
//        model.addAttribute("getGroups", containerService.findGroups());
        return "containers";
    }

    @GetMapping(value = "root-container/{id}")
    public String showGroups(@PathVariable int id, Model model, HttpServletRequest request) {
        model.addAttribute("container", new Container());
        Person loggedPerson = this.personService.findByLogin(request.getUserPrincipal().getName());
        if (loggedPerson.getRole().getId() == Role.STUDENT_ID) {
            model.addAttribute("getGroups", containerService.findGroups(id));
        } else if (loggedPerson.getRole().getId() == Role.TEACHER_ID) {
            model.addAttribute("getDepartments", containerService.findDepartments(id));
        }
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
