package ua.gradebook.service;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppServiceExtension<T extends ParentBean> extends AppService<T> {
    List<T> findListByObject(Object obj);
    T findByLogin(String login);
}
