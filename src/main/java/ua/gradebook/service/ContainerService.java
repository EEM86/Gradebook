package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.dao.ContainerDAO;
import ua.gradebook.model.dao.ContainerDAOImpl;

import java.util.List;

@Service
public class ContainerService implements AppServiceContainer<Container> {
    private final ContainerDAO<Container> containerDAO;

    @Autowired
    public ContainerService(ContainerDAOImpl containerDAO) {
        this.containerDAO = containerDAO;
    }

    @Override
    public List findAll() {
        return this.containerDAO.findAll();
    }

    @Override
    public Container findById(Integer id) {
        return this.containerDAO.findById(id);
    }

    @Override
    public Container findByName(String name) {
        return this.containerDAO.findByName(name);
    }

    @Override
    public boolean insert(Container item) {
        return this.containerDAO.insert(item);
    }

    @Override
    public boolean update(Container item) {
        return this.containerDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.containerDAO.delete(id);
    }

    /**
     * This request returns all groups from the table.
     */
    @Override
    public List<Container> findGroups() {
        return this.containerDAO.findGroups();
    }

    /**
     * This request returns child groups of a parent container
     */
    @Override
    public List<Container> findGroups(int rootId) {
        return this.containerDAO.findGroups(rootId);
    }

    /**
     * This request returns all departments from the table.
     */
    @Override
    public List<Container> findDepartments() {
        return this.containerDAO.findDepartments();
    }

    /**
     * This request returns child departments of a parent container
     */
    @Override
    public List<Container> findDepartments(int rootId) {
        return this.containerDAO.findDepartments(rootId);
    }

    @Override
    public Container findRootContainer(int groupId) {
        return this.containerDAO.findRootContainer(groupId); }
}
