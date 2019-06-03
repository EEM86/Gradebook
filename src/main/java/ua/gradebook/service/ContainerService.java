package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.ContainerDAOImpl;

import java.util.List;

@Service
public class ContainerService implements AppService {
    @Autowired
    ContainerDAOImpl containerDAO;

    @Override
    public List findAll() {
        return this.containerDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.containerDAO.findById(id);
    }

    @Override
    public ParentBean findByName(String name) {
        return this.containerDAO.findByName(name);
    }

    @Override
    public boolean insert(ParentBean item) {
        return this.containerDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.containerDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.containerDAO.delete(id);
    }
}
