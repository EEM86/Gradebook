package ua.gradebook.model.dao;


import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface DAO {
    //1 вариант
/*    List<Person> getPerson();
    Person findPersonById();
    Person findPersonByName(String name);
    Long insertPerson(Person item);
    void updatePerson(Person item);
    void deletePerson(Person item);*/

    List findAll();
    ParentBean findById(Integer id);
    ParentBean findByName(String name);
    boolean insert(ParentBean item);
    boolean update(ParentBean item);
    boolean delete(int id);
    Long getNextId();
}
