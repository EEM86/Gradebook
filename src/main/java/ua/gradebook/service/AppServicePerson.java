package ua.gradebook.service;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppServicePerson extends AppServiceExtension{
    ParentBean findByLogin(String login);
    List<ParentBean> findStudents();
    List<ParentBean> findTeacher();
}
