package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LessonsPlanController {

    private final AppServiceExtension<LessonsPlan> lessonsPlanService;
    private final PersonService personService;
    private final DisciplineService disciplineService;
    private final ContainerService containerService;


    private static final Logger logger = Logger.getLogger(LessonsPlanController.class);

    public LessonsPlanController(LessonsPlanService lessonsPlanService, PersonService personService, DisciplineService disciplineService, ContainerService containerService) {
        this.lessonsPlanService = lessonsPlanService;
        this.personService = personService;
        this.disciplineService = disciplineService;
        this.containerService = containerService;
    }

    @RequestMapping(value="lessonsplan", method = RequestMethod.GET)
    public String findRelativePlanById (HttpServletRequest request, Model model) {
        model.addAttribute("getGroups", containerService.findGroups());
        model.addAttribute("getDisciplines", disciplineService.findAll());
        model.addAttribute("getTeachers", personService.findTeacher());
        Integer id = (personService.findByLogin(request.getUserPrincipal().getName())).getId();
        model.addAttribute("lessonsplan", new LessonsPlan());
        if (Person.isAdmin()) {
            model.addAttribute("getLessonsplan", lessonsPlanService.findAll());
            return "lessonsplan";
        } else {
            model.addAttribute("getLessonsplan", lessonsPlanService.findListByObject(id));
        }
        logger.info("lessonsplan loaded");
        return "lessonsplan";
    }

    @PostMapping(value = "/lessonsplan/add")
    public String addLessonsPlan(@ModelAttribute("lessonsplan") LessonsPlan lessonsPlan){
        if (lessonsPlan.getId() == null) {
            this.lessonsPlanService.insert(lessonsPlan);
        } else {
            this.lessonsPlanService.update(lessonsPlan);
        }

        return "redirect:/lessonsplan";
    }

    @RequestMapping(value = "/lessonsplan/delete/{id}")
    public String deleteLessonsPlan(@PathVariable("id") int id){
        this.lessonsPlanService.delete(id);
        return "redirect:/lessonsplan";
    }

    @RequestMapping(value = "/lessonsplan/edit/{id}")
    public String editLessonsPlan(@PathVariable("id") int id, Model model){
        model.addAttribute("getGroups", containerService.findGroups());
        model.addAttribute("getDisciplines", disciplineService.findAll());
        model.addAttribute("getTeachers", personService.findTeacher());
        model.addAttribute("lessonsplan", this.lessonsPlanService.findById(id));
        model.addAttribute("getLessonsplan", this.lessonsPlanService.findAll());
        return "lessonsplan";
    }
}
