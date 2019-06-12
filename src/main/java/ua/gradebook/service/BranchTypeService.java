package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DAO;

import java.util.List;

@Service(value="BranchTypeService")
public class BranchTypeService implements AppService {
    @Autowired
    @Qualifier("BranchTypeDAO")
    private DAO branchTypeDAO;

    @Override
    public List findAll() {
        return this.branchTypeDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.branchTypeDAO.findById(id);
    }

    @Override
    public ParentBean findByName(String name) {
        return this.branchTypeDAO.findByName(name);
    }

    @Override
    public boolean insert(ParentBean item) {
        return this.branchTypeDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.branchTypeDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.branchTypeDAO.delete(id);
    }
}
