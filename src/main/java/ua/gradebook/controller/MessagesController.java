package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.mail.SimpleOrderManager;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;
import ua.gradebook.service.PersonService;

@Controller
public class MessagesController {

    @Autowired
    @Qualifier("MessageService")
    private AppServiceExtension messageService;

    @Autowired
    private SimpleOrderManager simpleOrderManager;

    @Autowired
    private PersonService personService;

    private static final Logger logger = Logger.getLogger(MessagesController.class);

    @GetMapping(value = "messages")
    public String showAllMessage (Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("getMessages", messageService.findAll());
        logger.info("messages load");
        return "messages";
    }

    @GetMapping(value = "messages/{id}")
    public String showMessagesById (@PathVariable("id") Integer id, Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("getMessages", messageService.findListByObject(id));
        logger.info("messages load");
        return "messages";
    }

    @PostMapping(value = "/messages/add")
    public String addMessage(@ModelAttribute("message") Message message){
        if (message.getId() == null) {
            this.messageService.insert(message);
        } else {
            this.messageService.update(message);
        }
        simpleOrderManager.sendMessage((Person) personService.findById(message.getReceiverId()));
        return "redirect:/messages";
    }

    @RequestMapping(value = "/messages/delete/{id}")
    public String deleteMessage(@PathVariable("id") int id){
        this.messageService.delete(id);
        return "redirect:/messages";
    }
}
