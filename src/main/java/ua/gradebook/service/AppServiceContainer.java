package ua.gradebook.service;

import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppServiceContainer<T extends ParentBean> extends AppService<T> {
    List<T> findGroups();
    List<T> findDepatments();
}
