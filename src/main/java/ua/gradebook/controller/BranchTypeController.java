package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.BranchType;
import ua.gradebook.service.BranchTypeService;

@Controller
public class BranchTypeController {
    @Autowired
    private BranchTypeService branchTypeService;

    private static final Logger logger = Logger.getLogger(BranchTypeController.class);

    @GetMapping(value = "branchtypes")
    public String showAllBranchTypes(Model model) {
        model.addAttribute("branchtype", new BranchType());
        model.addAttribute("getbranchtypes", branchTypeService.findAll());
        logger.info("branchtypes load");
        return "branchtypes";
    }

    @PostMapping(value = "/branchtypes/add")
    public String addBranchType(@ModelAttribute("branchtype") BranchType branchType){
        if (branchType.getId() == null) {
            this.branchTypeService.insert(branchType);
        } else {
            this.branchTypeService.update(branchType);
        }
        return "redirect:/branchtypes";
    }

    @RequestMapping(value = "/branchtypes/delete/{id}")
    public String deleteBranchType(@PathVariable("id") int id){
        this.branchTypeService.delete(id);
        return "redirect:/branchtypes";
    }

    @RequestMapping(value = "/branchtypes/edit/{id}")
    public String editBranchTypes(@PathVariable("id") int id, Model model){
        model.addAttribute("branchtype", this.branchTypeService.findById(id));
        model.addAttribute("getbranchtypes", this.branchTypeService.findAll());
        return "branchtypes";
    }
}
