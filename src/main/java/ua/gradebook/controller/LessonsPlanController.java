package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;

@Controller
public class LessonsPlanController {
    @Autowired
    @Qualifier("LessonsPlanService")
    private AppServiceExtension lessonsPlanService;

    private static final Logger logger = Logger.getLogger(LessonsPlanController.class);

    @GetMapping(value = "lessonsplan")
    public String showAllLessonsPlans (Model model) {
        model.addAttribute("lessonsplan", new LessonsPlan());
        model.addAttribute("getLessonsplan", lessonsPlanService.findAll());
        logger.info("lessonsplan load");
        return "lessonsplan";
    }

    @GetMapping(value = "lessonsplan/{id}")
    public String findRelativePlanById (@PathVariable("id") Integer id, Model model) {
        if (Person.isAdmin()) {
            return "redirect:/lessonsplan";
        }
        model.addAttribute("lessonsplan", new LessonsPlan());
        model.addAttribute("getLessonsplan", lessonsPlanService.findListByObject(id));
        logger.info("lessonsplan load");
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
