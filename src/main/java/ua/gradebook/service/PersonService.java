package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.dao.PersonDAO;
import ua.gradebook.model.dao.PersonDAOImpl;

import java.util.List;

@Service
public class PersonService implements AppServicePerson<Person> {
    private final PersonDAO<Person> personDAO;

    @Autowired
    public PersonService(PersonDAOImpl personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> findAll() {
        return this.personDAO.findAll();
    }

    @Override
    public List<Person> findStudents() {
        return this.personDAO.findStudents();
    }

    @Override
    public List<Person> findTeacher() {
        return this.personDAO.findTeacher();
    }

    @Override
    public Person findById(Integer id) {
        return this.personDAO.findById(id);
    }

    @Override
    public Person findByName(String name) {
       return this.personDAO.findByName(name);
    }

    @Override
    public Person findByLogin(String login) {return this.personDAO.findByLogin(login); }

    @Override
    public List<Person> findAllWithoutOneId(Integer id) {
        return this.personDAO.findAllWithoutOneId(id);
    }

    @Override
    public List<Person> findListByObject(Object obj) {
        return this.personDAO.findListByObject(obj);
    }

    @Override
    public boolean insert(Person item) {
        return this.personDAO.insert(item);
    }

    @Override
    public boolean update(Person item) {
        return this.personDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.personDAO.delete(id);
    }
}
