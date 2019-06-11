package ua.gradebook.model.dao;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface DAOExtension extends DAO {
    List<ParentBean> findListByName(String text);
    List<ParentBean> findListById(Integer id);
    ParentBean findByLogin(String login);
}
