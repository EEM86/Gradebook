package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.dao.PersonDAOImpl;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonDAOImpl personDAO;

    public List<ParentBean> findAll() {
        return personDAO.findAll();
    }

    public ParentBean findById(Integer id) {
        return personDAO.findById(id);
    }

    public ParentBean findByName(String name) {
       return personDAO.findByName(name);
    }

    public boolean insert(ParentBean item) {
        return personDAO.insert(item);
    }

    public boolean update(ParentBean oldItem, ParentBean newItem) {
        return personDAO.update(oldItem, newItem);
    }

    public boolean delete(int id) {
        return personDAO.delete(id);
    }

}
