package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.RoleDAOImpl;

import java.util.List;

@Service
public class RoleService implements AppService {
    @Autowired
    RoleDAOImpl roleDAO;

    @Override
    public List findAll() {
        return this.roleDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.roleDAO.findById(id);
    }

    @Override
    public ParentBean findByName(String name) {
        return this.roleDAO.findByName(name);
    }

    @Override
    public boolean insert(ParentBean item) {
        return this.roleDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.roleDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.roleDAO.delete(id);
    }
}
