package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.mail.SimpleOrderManager;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;
import ua.gradebook.service.MessageService;
import ua.gradebook.service.PersonService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessagesController {
    private static final Logger logger = Logger.getLogger(MessagesController.class);

    private final AppServiceExtension<Message> messageService;
    private final SimpleOrderManager simpleOrderManager;
    private final PersonService personService;

    @Autowired
    public MessagesController(MessageService messageService, SimpleOrderManager simpleOrderManager, PersonService personService) {
        this.messageService = messageService;
        this.simpleOrderManager = simpleOrderManager;
        this.personService = personService;
    }

    @RequestMapping(value="messages", method = RequestMethod.GET)
    public String showMessagesById(HttpServletRequest request, Model model) {
        Person person = personService.findByLogin(request.getUserPrincipal().getName());
        model.addAttribute("getPersons", personService.findAllWithoutOneId(person.getId()));
        model.addAttribute("idSender", person);
        model.addAttribute("message", new Message());
        if (Person.isAdmin()) {
            model.addAttribute("getMessages", messageService.findAll());
        } else {
            model.addAttribute("getMessages", messageService.findListByObject(person.getId()));
        }
        logger.info("messages load");
        return "messages";
    }

    @RequestMapping(value = "/messages/delete/{id}")
    public String deleteMessage(@PathVariable("id") int id){
        this.messageService.delete(id);
        return "redirect:/messages";
    }

    @PostMapping(value = "/messages/add")
    public String addMessage(@ModelAttribute("message") Message message){
        if (message.getId() == null) {
            this.messageService.insert(message);
        } else {
            this.messageService.update(message);
        }
        Person receiver = personService.findById(message.getReceiver().getId());
        simpleOrderManager.sendMessage(receiver);
        return "redirect:/messages";
    }
}
