package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Discipline;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("discipline")
public class DisciplineRestController {
    @Autowired
    DisciplineService disciplineService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllDisciplines() {
        List<ParentBean> parentBeanList = disciplineService.findAll();
        return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDiscipline(@RequestBody Discipline discipline) {
        try {
            disciplineService.insert(discipline);
        } catch (Throwable s) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDiscipline(@PathVariable int id, @RequestBody Discipline discipline) {
        try {
            Discipline model = (Discipline) disciplineService.findById(id);
            discipline.setId(model.getId());
            disciplineService.update(discipline);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDiscipline(@PathVariable int id) {
        try {
            Discipline model = (Discipline) disciplineService.findById(id);
            disciplineService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findDisciplineById(@PathVariable int id) {
        try {
            Discipline discipline = (Discipline) disciplineService.findById(id);
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
