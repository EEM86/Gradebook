package ua.gradebook.model.dao;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface DAO<T extends ParentBean> {
    List<T> findAll();
    T findById(Integer id);
    T findByName(String name);
    boolean insert(T item);
    boolean update(T item);
    boolean delete(int id);
}
