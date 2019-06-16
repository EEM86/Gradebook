package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.service.AppServiceExtension;
import ua.gradebook.service.JournalService;

import java.util.List;

@RestController
@RequestMapping("journal-rest")
public class JournalRestController {
    private final AppServiceExtension<GradesJournal> journalService;

    @Autowired
    public JournalRestController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllJournals() {
        List<GradesJournal> parentBeanList = journalService.findAll();
        return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJournal(@RequestBody GradesJournal journal) {
        try {
            journalService.insert(journal);
        } catch (Throwable s) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateJournal(@PathVariable int id, @RequestBody GradesJournal journal) {
        try {
            GradesJournal model = journalService.findById(id);
            journal.setId(model.getId());
            journalService.update(journal);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable int id) {
        try {
            journalService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findJournalById(@PathVariable int id) {
        try {
            GradesJournal journal  = journalService.findById(id);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
