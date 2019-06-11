package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Role;
import ua.gradebook.service.AppService;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleRestController {
    @Autowired
    @Qualifier("RoleService")
    AppService roleService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllRoles() {
        List<ParentBean> parentBeanList = roleService.findAll();
        return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        try {
            roleService.insert(role);
        } catch (Throwable s) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody Role role) {
        try {
            Role model = (Role) roleService.findById(id);
            role.setId(model.getId());
            roleService.update(role);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        try {
            roleService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findRoleById(@PathVariable int id) {
        try {
            Role role = (Role) roleService.findById(id);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
