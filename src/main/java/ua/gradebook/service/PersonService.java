package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.dao.PersonDAOImpl;

import java.util.List;

@Service
public class PersonService implements AppService {
    @Autowired
    PersonDAOImpl personDAO;

    public List<ParentBean> findAll() {
        return this.personDAO.findAll();
    }

    public ParentBean findById(Integer id) {
        return this.personDAO.findById(id);
    }

    public ParentBean findByName(String name) {
       return this.personDAO.findByName(name);
    }

    public List<ParentBean> findNames(String text) {
       return this.personDAO.findNames(text);
    }

    public boolean insert(ParentBean item) {
        return this.personDAO.insert(item);
    }

    public boolean update(ParentBean item) {
        return this.personDAO.update(item);
    }

    public boolean delete(int id) {
        return this.personDAO.delete(id);
    }

}
