package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;
import ua.gradebook.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonRestController {

    private final AppServiceExtension<Person> personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(){
        List<Person> parentBeanList = personService.findAll();
        return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPerson(@RequestBody Person person){
        try {
            personService.insert(person);
        }catch (Throwable s) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody Person person) {
        try {
            Person model = personService.findById(id);
            person.setId(model.getId());
            personService.update(person);
        return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id){
        try {
            personService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            Person person = personService.findById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
