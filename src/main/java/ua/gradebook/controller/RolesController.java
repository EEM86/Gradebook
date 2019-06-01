package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.RoleDAOImpl;

import java.util.List;

@Controller
public class RolesController {
    @Autowired
    private RoleDAOImpl dao;

    private List<ParentBean> rolesList;
    private static final Logger logger = Logger.getLogger(RolesController.class);

    @RequestMapping(value = "/showAllRoles", method = RequestMethod.GET)
    public ModelAndView showAllRoles() {
        rolesList = dao.findAll();
        logger.info("rolesList");
        return new ModelAndView("showAllStudents", "studentList", rolesList);
        //logger.error("IOException error ", e);
    }
}
