package ua.gradebook.model.dao;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface ContainerDAO<T extends ParentBean> extends DAO<T> {
    List<T> findGroups();
    List<T> findDepatments();
}
