package ua.gradebook.model.dao;


import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface DAO {
    List findAll();
    ParentBean findById(Integer id);
    ParentBean findByName(String name);
    boolean insert(ParentBean item);
    boolean update(ParentBean item);
    boolean delete(int id);
}
