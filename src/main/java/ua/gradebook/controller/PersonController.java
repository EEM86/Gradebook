package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.beans.Role;
import ua.gradebook.service.AppService;
import ua.gradebook.service.AppServicePerson;
import ua.gradebook.service.ContainerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class PersonController {
    private final AppServicePerson<Person> personService;
    private final AppService<Role> roleService;
    private final ContainerService containerService;

    private static final Logger logger = Logger.getLogger(PersonController.class);

    @Autowired
    public PersonController(AppServicePerson<Person> personService, AppService<Role> roleService, ContainerService containerService) {
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
    public String addPerson(@Valid @ModelAttribute("person") Person person, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("Errors:" + result.toString());
            return "/persons";
        }
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

    @GetMapping(value="persons/{id}")
    public String getConcretePerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", new Person());
        Person concretePerson = this.personService.findById(id);
        ArrayList<Person> res = new ArrayList<>();
        res.add(concretePerson);
        model.addAttribute("getPersons", res);
        return "persons";
    }

    @RequestMapping(value="profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request, Model model) {
        model.addAttribute("person", this.personService.findByLogin(request.getUserPrincipal().getName()));
        return "profile";
    }

    @GetMapping(value="persons/container/{id}")
    public String getPersonsFromContainer(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        model.addAttribute("person", new Person());
        Person loggedPerson = personService.findByLogin(request.getUserPrincipal().getName());
        if (loggedPerson.getRole().getId() == Role.STUDENT_ID) {
            model.addAttribute("getPersons", personService.findPersonsFromGroup(id));
        } else if (loggedPerson.getRole().getId() == Role.TEACHER_ID) {
            model.addAttribute("getPersons", personService.findPersonsFromDepartment(id));
        }
        return "persons";
    }
}
