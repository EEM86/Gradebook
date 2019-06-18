package ua.gradebook.model.dao;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface DAOExtension<T extends ParentBean> extends DAO<T> {
    List<T> findListByObject(Object obj);
}
