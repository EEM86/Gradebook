package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.Person;
import ua.gradebook.service.JournalService;
import ua.gradebook.service.PersonService;

import java.util.Collection;

@Controller
public class JournalController {

    @Autowired
    private JournalService journalService;

    private static final Logger logger = Logger.getLogger(JournalController.class);

    @GetMapping(value = "journal")
    public String showAllJournals(Model model) {
        model.addAttribute("journal", new GradesJournal());
        model.addAttribute("getJournals", journalService.findAll());
        logger.info("journal load");
        return "journal";
    }

    @GetMapping(value = "journal/{id}")
    public String showRelativeJournalById (@PathVariable("id") int id, Model model) {
        if (Person.isAdmin()) {
            return "redirect:/journal";
        }
        model.addAttribute("journal", new GradesJournal());
        model.addAttribute("getJournals", journalService.findRelativeDataById(id));
        logger.info("journals load");
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
