package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;
import ua.gradebook.service.PersonService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LessonsPlanController {

    private final AppServiceExtension lessonsPlanService;
    private final PersonService personService;


    private static final Logger logger = Logger.getLogger(LessonsPlanController.class);

    public LessonsPlanController(@Qualifier("LessonsPlanService") AppServiceExtension lessonsPlanService, PersonService personService) {
        this.lessonsPlanService = lessonsPlanService;
        this.personService = personService;
    }

    @RequestMapping(value="lessonsplan", method = RequestMethod.GET)
    public String findRelativePlanById (HttpServletRequest request, Model model) {
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
        model.addAttribute("lessonsplan", this.lessonsPlanService.findById(id));
        model.addAttribute("getLessonsplan", this.lessonsPlanService.findAll());
        return "lessonsplan";
    }
}
