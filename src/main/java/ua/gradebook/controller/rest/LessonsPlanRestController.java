package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.service.AppServiceExtension;

import java.util.List;

@RestController
@RequestMapping("lessonsplan-rest")
public class LessonsPlanRestController {
    @Autowired
    @Qualifier("LessonsPlanService")
    AppServiceExtension lessonsPlanService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllLessonsPlans() {
        List<ParentBean> parentBeanList = lessonsPlanService.findAll();
        return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLessonsPlan(@RequestBody LessonsPlan lessonsPlan) {
        try {
            lessonsPlanService.insert(lessonsPlan);
        } catch (Throwable s) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLessonsPlan(@PathVariable int id, @RequestBody LessonsPlan lessonsPlan) {
        try {
            LessonsPlan model = (LessonsPlan) lessonsPlanService.findById(id);
            lessonsPlan.setId(model.getId());
            lessonsPlanService.update(lessonsPlan);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteLessonsPlan(@PathVariable int id) {
        try {
            lessonsPlanService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findLessonsPlanById(@PathVariable int id) {
        try {
            LessonsPlan lessonsPlan = (LessonsPlan) lessonsPlanService.findById(id);
            return new ResponseEntity<>(lessonsPlan, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
