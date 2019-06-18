package ua.gradebook.model.dao;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface PersonDAO extends DAOExtension {
    ParentBean findByLogin(String login);
    List<ParentBean> findStudents();
    List<ParentBean> findTeacher();
}
