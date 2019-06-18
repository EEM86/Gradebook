package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Role;
import ua.gradebook.model.dao.DAO;
import ua.gradebook.model.dao.RoleDAOImpl;

import java.util.List;

@Service
public class RoleService implements AppService<Role> {
    private final DAO<Role> roleDAO;

    @Autowired
    public RoleService(RoleDAOImpl roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> findAll() { return this.roleDAO.findAll(); }

    @Override
    public Role findById(Integer id) {
        return this.roleDAO.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return this.roleDAO.findByName(name);
    }

    @Override
    public boolean insert(Role item) {
        return this.roleDAO.insert(item);
    }

    @Override
    public boolean update(Role item) {
        return this.roleDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.roleDAO.delete(id);
    }
}
