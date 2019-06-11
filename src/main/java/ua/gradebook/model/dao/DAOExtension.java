package ua.gradebook.model.dao;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface DAOExtension extends DAO {
    List<ParentBean> findListByObject(Object obj);
    ParentBean findByLogin(String login);
}
