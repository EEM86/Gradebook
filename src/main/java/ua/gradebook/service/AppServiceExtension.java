package ua.gradebook.service;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppServiceExtension extends AppService {
    List<ParentBean> findListByObject(Object obj);
    ParentBean findByLogin(String login);
}
