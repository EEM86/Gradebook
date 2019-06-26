package ua.gradebook.service;

import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.ParentBean;

import java.util.List;

public interface AppServiceContainer<T extends ParentBean> extends AppService<T> {
    List<T> findGroups(int rootId);
    List<T> findDepartments(int rootId);
    List<T> findGroups();
    List<T> findDepartments();
    Container findRootContainer(int groupId);
}
