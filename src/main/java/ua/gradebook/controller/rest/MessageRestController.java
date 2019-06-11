package ua.gradebook.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("message-rest")
public class MessageRestController {
        @Autowired
        MessageService messageService;

        @GetMapping(value = "/all")
        public ResponseEntity<?> getAllMessages() {
            List<ParentBean> parentBeanList = messageService.findAll();
            return new ResponseEntity<>(parentBeanList, HttpStatus.OK);
        }

        @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> createMessage(@RequestBody Message message) {
            try {
                messageService.insert(message);
            } catch (Throwable s) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> updateMessage(@PathVariable int id, @RequestBody Message message) {
            try {
                Message model = (Message) messageService.findById(id);
                message.setId(model.getId());
                messageService.update(message);
                return new ResponseEntity<>(model, HttpStatus.OK);
            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<?> deleteMessage(@PathVariable int id) {
            try {
                messageService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<?> findMessageById(@PathVariable int id) {
            try {
                Message message = (Message) messageService.findById(id);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
