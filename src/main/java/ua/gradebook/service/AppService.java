package ua.gradebook.service;


import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppService<T extends ParentBean> {
    List<T> findAll();
    T findById(Integer id);
    T findByName(String name);
    boolean insert(T item);
    boolean update(T item);
    boolean delete(int id);
}
