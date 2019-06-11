package ua.gradebook.service;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppServiceExtension extends AppService {
    List<ParentBean> findListByName(String text);
    List<ParentBean> findListById(Integer id);
    ParentBean findByLogin(String login);
}