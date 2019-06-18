package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DAOExtension;
import ua.gradebook.model.dao.PersonDAO;

import java.util.List;

@Service(value="PersonService")
public class PersonService implements AppServicePerson {
    @Autowired
    @Qualifier("PersonDAO")
    private PersonDAO personDAO;

    @Override
    public List<ParentBean> findAll() {
        return this.personDAO.findAll();
    }

    @Override
    public List<ParentBean> findStudents() {
        return this.personDAO.findStudents();
    }

    @Override
    public List<ParentBean> findTeacher() {
        return this.personDAO.findTeacher();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.personDAO.findById(id);
    }

    @Override
    public ParentBean findByName(String name) {
       return this.personDAO.findByName(name);
    }

    @Override
    public ParentBean findByLogin(String login) {return this.personDAO.findByLogin(login); }

    @Override
    public List<ParentBean> findListByObject(Object obj) {
        return this.personDAO.findListByObject(obj);
    }

    @Override
    public boolean insert(ParentBean item) {
        return this.personDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.personDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.personDAO.delete(id);
    }
}
