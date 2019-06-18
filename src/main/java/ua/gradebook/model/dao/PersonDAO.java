package ua.gradebook.model.dao;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface PersonDAO<T extends ParentBean> extends DAOExtension<T> {
    T findByLogin(String login);
    List<T> findStudents();
    List<T> findTeacher();
}
