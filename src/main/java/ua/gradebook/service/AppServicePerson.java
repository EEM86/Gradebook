package ua.gradebook.service;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppServicePerson<T extends ParentBean> extends AppServiceExtension<T>{
    T findByLogin(String login);
    List<T> findAllWithoutOneId(Integer id);
    List<T> findPersonsFromGroup(Integer id);
    List<T> findPersonsFromDepartment(Integer id);
    List<T> findStudents();
    List<T> findTeacher();
}
