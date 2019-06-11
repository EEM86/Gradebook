package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.BranchType;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.service.AppService;

import java.util.List;

@RestController
@RequestMapping("branchtypes-rest")
public class BranchTypeRestController {
    @Autowired
    @Qualifier("BranchTypeService")
    AppService branchTypeService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllBranchTypes() {
        List<ParentBean> parentBeanList = branchTypeService.findAll();
        return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBranchType(@RequestBody BranchType branchType) {
        try {
            branchTypeService.insert(branchType);
        } catch (Throwable s) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBranchType(@PathVariable int id, @RequestBody BranchType branchType) {
        try {
            BranchType model = (BranchType) branchTypeService.findById(id);
            branchType.setId(model.getId());
            branchTypeService.update(branchType);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBranchType(@PathVariable int id) {
        try {
            branchTypeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            BranchType person = (BranchType) branchTypeService.findById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
