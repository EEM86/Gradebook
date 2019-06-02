package ua.gradebook.service;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppService {
    List findAll();
    ParentBean findById(Integer id);
    ParentBean findByName(String name);
    boolean insert(ParentBean item);
    boolean update(ParentBean item);
    boolean delete(int id);
}
