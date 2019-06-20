package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.beans.Role;
import ua.gradebook.service.AppService;
import ua.gradebook.service.AppServicePerson;
import ua.gradebook.service.ContainerService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonController {
    private final AppServicePerson personService;
    private final AppService<Role> roleService;
    private final ContainerService containerService;

    private static final Logger logger = Logger.getLogger(PersonController.class);

    @Autowired
    public PersonController(AppServicePerson personService, AppService<Role> roleService, ContainerService containerService) {
        this.personService = personService;
        this.roleService = roleService;
        this.containerService = containerService;
    }

    @GetMapping(value="persons")
    public String getPersons(Model model) {
        model.addAttribute("getRoles", roleService.findAll());
        model.addAttribute("getGroups", containerService.findGroups());
        model.addAttribute("getDepartments", containerService.findDepartments());
        model.addAttribute("person", new Person());
        model.addAttribute("getPersons", personService.findAll());
        model.addAttribute("getCurators", personService.findAll());
        logger.info("persons load");
        return "persons";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person){
        if (person.getId() == null) {
            this.personService.insert(person);
        } else {
            this.personService.update(person);
        }
        return "redirect:/persons";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deletePerson(@PathVariable("id") int id){
        this.personService.delete(id);
        return "redirect:/persons";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("getRoles", roleService.findAll());
        model.addAttribute("getGroups", containerService.findGroups());
        model.addAttribute("getDepartments", containerService.findDepartments());
        model.addAttribute("person", this.personService.findById(id));
        model.addAttribute("getPersons", this.personService.findAll());
        model.addAttribute("getCurators", personService.findAllWithoutOneId(id));
        return "persons";
    }

    @RequestMapping(value="persons", method = RequestMethod.POST)
    public String searchPerson(@RequestParam("lastName") String lastName, Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("getPersons", this.personService.findListByObject(lastName));
        return "persons";
    }

    @RequestMapping(value="profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request, Model model) {
        model.addAttribute("person", this.personService.findByLogin(request.getUserPrincipal().getName()));
        return "profile";
    }
}
