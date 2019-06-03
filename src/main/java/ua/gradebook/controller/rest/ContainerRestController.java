package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.service.ContainerService;
import ua.gradebook.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("container")
public class ContainerRestController {
    @Autowired
    ContainerService containerService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllContainers() {
        List<ParentBean> parentBeanList = containerService.findAll();
        return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createContainer(@RequestBody Container container) {
        try {
            containerService.insert(container);
        } catch (Throwable s) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateContainer(@PathVariable int id, @RequestBody Container container) {
        try {
            Container model = (Container) containerService.findById(id);
            container.setId(model.getId());
            containerService.update(container);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteContainer(@PathVariable int id) {
        try {
            Container model = (Container) containerService.findById(id);
            containerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            Container person = (Container) containerService.findById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
