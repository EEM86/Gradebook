package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.gradebook.model.beans.Roles;
import ua.gradebook.model.dao.DAOImpl;
import ua.gradebook.model.dao.RoleDAO;

import javax.management.relation.Role;
import java.util.List;

public class RolesController {
    @Autowired
    private DAOImpl dao;

    private List<Roles> rolesList;
    private static final Logger logger = Logger.getLogger(RolesController.class);

    @RequestMapping(value = "/showAllRoles", method = RequestMethod.GET)
    public ModelAndView showAllRoles() {
        rolesList = dao.getRoles();
        logger.info("rolesList");
        return new ModelAndView("showAllStudents", "studentList", rolesList);
        //logger.error("IOException error ", e);
    }
}
