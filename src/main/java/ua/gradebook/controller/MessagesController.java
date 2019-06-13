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

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessagesController {
    private static final Logger logger = Logger.getLogger(MessagesController.class);

    private final AppServiceExtension messageService;
    private final SimpleOrderManager simpleOrderManager;
    private final PersonService personService;

    @Autowired
    public MessagesController(@Qualifier("MessageService") AppServiceExtension messageService, SimpleOrderManager simpleOrderManager, PersonService personService) {
        this.messageService = messageService;
        this.simpleOrderManager = simpleOrderManager;
        this.personService = personService;
    }

    @GetMapping(value = "messages")
    public String showAllMessage (Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("getMessages", messageService.findAll());
        logger.info("messages load");
        return "messages";
    }

    @RequestMapping(value="personalmessages", method = RequestMethod.GET)
    public String showMessagesById(HttpServletRequest request, Model model) {
        if (Person.isAdmin()) {
            return "redirect:/messages";
        }
        model.addAttribute("message", new Message());
        Integer id = (personService.findByLogin(request.getUserPrincipal().getName())).getId();
        model.addAttribute("idSender", id);
        model.addAttribute("getMessages", messageService.findListByObject(id));
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
        simpleOrderManager.sendMessage((Person) personService.findById(message.getReceiverId()));
        return "redirect:/messages";
    }


}
