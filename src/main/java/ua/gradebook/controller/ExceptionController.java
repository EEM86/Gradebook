package ua.gradebook.controller;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(SQLException.class)
    public String handleException(HttpServletRequest req, Exception e) {
        logger.error("SQL Exception: " + req.getRequestURL() + " appeared " + e);
        return "error";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleEmptyResultException(HttpServletRequest req, Exception e) {
        logger.error("Empty Result Data Access Exception: " + req.getRequestURL() + " appeared " + e);
        return "error";
    }
}
