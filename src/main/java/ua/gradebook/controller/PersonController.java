package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;

@Controller
public class PersonController {
    @Autowired
    @Qualifier("PersonService")
    private AppServiceExtension personService;

    private static final Logger logger = Logger.getLogger(PersonController.class);

    @GetMapping(value="persons")
    public String getPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("getPersons", personService.findAll());
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
        model.addAttribute("person", this.personService.findById(id));
        model.addAttribute("getPersons", this.personService.findAll());
        return "persons";
    }

    @RequestMapping(value="persons", method = RequestMethod.POST)
    public String searchPerson(@RequestParam("lastName") String lastName, Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("getPersons", this.personService.findListByName(lastName));
        return "persons";
    }

    @RequestMapping(value="profile/{id}", method = RequestMethod.GET)
    public String profile(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", this.personService.findById(id));
        return "profile";
    }
}
