package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Message;
import ua.gradebook.service.MessageService;

@Controller
public class MessagesController {

    @Autowired
    private MessageService messageService;

    private static final Logger logger = Logger.getLogger(MessagesController.class);

    @GetMapping(value = "messages")
    public String showAllMessage (Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("getMessages", messageService.findAll());
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

        return "redirect:/messages";
    }

    @RequestMapping(value = "/messages/delete/{id}")
    public String deleteMessage(@PathVariable("id") int id){
        this.messageService.delete(id);
        return "redirect:/messages";
    }

    @RequestMapping(value = "/messages/edit/{id}")
    public String editMessage(@PathVariable("id") int id, Model model){
        model.addAttribute("message", this.messageService.findById(id));
        model.addAttribute("getMessages", this.messageService.findAll());
        return "messages";
    }
}
