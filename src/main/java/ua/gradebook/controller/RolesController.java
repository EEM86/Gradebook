package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.gradebook.model.beans.Role;
import ua.gradebook.service.AppService;
import ua.gradebook.service.RoleService;

@Controller
public class RolesController {
    private final AppService<Role> roleService;

    private static final Logger logger = Logger.getLogger(RolesController.class);

    @Autowired
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "roles")
    public String showAllRoles(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("getRoles", roleService.findAll());
        logger.info("roles load");
        return "roles";
    }

    @PostMapping(value = "/roles/add")
    public String addRole(@ModelAttribute("role") Role role){
        if (role.getId() == null) {
            this.roleService.insert(role);
        } else {
            this.roleService.update(role);
        }
        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/delete/{id}")
    public String deleteRole(@PathVariable("id") int id){
        this.roleService.delete(id);
        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/edit/{id}")
    public String editRole(@PathVariable("id") int id, Model model){
        model.addAttribute("role", this.roleService.findById(id));
        model.addAttribute("getRoles", this.roleService.findAll());
        return "roles";
    }
}
