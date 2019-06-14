package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.AppServiceExtension;
import ua.gradebook.service.PersonService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JournalController {

    private final AppServiceExtension journalService;
    private final PersonService personService;

    private static final Logger logger = Logger.getLogger(JournalController.class);

    public JournalController( @Qualifier("JournalService")AppServiceExtension journalService,
                              PersonService personService) {
        this.journalService = journalService;
        this.personService = personService;
    }

    @RequestMapping(value="journal", method = RequestMethod.GET)
    public String showRelativeJournalById(HttpServletRequest request, Model model) {
        Integer id = (personService.findByLogin(request.getUserPrincipal().getName())).getId();
        model.addAttribute("teacher", id);
        model.addAttribute("journal", new GradesJournal());
        if (Person.isAdmin()) {
            model.addAttribute("getJournals", journalService.findAll());
            return "journal";
        } else {
            model.addAttribute("getJournals", journalService.findListByObject(id));
        }
        logger.info("journals loaded");
        return "journal";
    }

    @PostMapping(value = "/journal/add")
    public String addJournal(@ModelAttribute("journal") GradesJournal journal){
        if (journal.getId() == null) {
            this.journalService.insert(journal);
        } else {
            this.journalService.update(journal);
        }

        return "redirect:/journal";
    }

    @RequestMapping(value = "/journal/delete/{id}")
    public String deleteJournal(@PathVariable("id") int id){
        this.journalService.delete(id);
        return "redirect:/journal";
    }

    @RequestMapping(value = "/journal/edit/{id}")
    public String editJournal(@PathVariable("id") int id, Model model){
        model.addAttribute("journal", this.journalService.findById(id));
        model.addAttribute("getJournals", this.journalService.findAll());
        return "journal";
    }
}
