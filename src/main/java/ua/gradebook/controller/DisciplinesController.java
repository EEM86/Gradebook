package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Discipline;
import ua.gradebook.service.DisciplineService;

@Controller
public class DisciplinesController {
    @Autowired
    private DisciplineService disciplineService;

    private static final Logger logger = Logger.getLogger(RolesController.class);

    @GetMapping(value = "disciplines")
    public String showAllDisciplines(Model model) {
        model.addAttribute("discipline", new Discipline());
        model.addAttribute("getDisciplines", disciplineService.findAll());
        return "disciplines";
    }

    @PostMapping(value = "/disciplines/add")
    public String addDiscipline(@ModelAttribute("discipline") Discipline discipline){
        if (discipline.getId() == null) {
            this.disciplineService.insert(discipline);
        } else {
            this.disciplineService.update(discipline);
        }
        return "redirect:/disciplines";
    }

    @RequestMapping(value = "/disciplines/delete/{id}")
    public String deleteDiscipline(@PathVariable("id") int id){
        this.disciplineService.delete(id);
        return "redirect:/disciplines";
    }

    @RequestMapping(value = "/disciplines/edit/{id}")
    public String editDiscipline(@PathVariable("id") int id, Model model){
        model.addAttribute("discipline", this.disciplineService.findById(id));
        model.addAttribute("getDisciplines", this.disciplineService.findAll());
        return "disciplines";
    }
}
