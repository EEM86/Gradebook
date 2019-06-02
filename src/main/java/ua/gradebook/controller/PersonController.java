package ua.gradebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.PersonService;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value="/persons")
    public String getPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("getPersons", personService.findAll());
        return "persons";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person){
        this.personService.insert(person);

        return "redirect:/persons";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deletePerson(@PathVariable("id") int id){
        this.personService.delete(id);

        return "redirect:/persons";
    }


    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("personId", this.personService.findById(id));
        model.addAttribute("getPersons", this.personService.findAll());
        return "persons";
    }
}
