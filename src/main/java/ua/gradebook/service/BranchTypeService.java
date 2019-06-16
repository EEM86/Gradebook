package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.BranchType;
import ua.gradebook.model.dao.BranchTypeDAOImpl;
import ua.gradebook.model.dao.DAO;

import java.util.List;

@Service
public class BranchTypeService implements AppService<BranchType> {

    private final DAO<BranchType> branchTypeDAO;

    @Autowired
    public BranchTypeService(BranchTypeDAOImpl branchTypeDAO) {
        this.branchTypeDAO = branchTypeDAO;
    }

    @Override
    public List<BranchType> findAll() {
        return this.branchTypeDAO.findAll();
    }

    @Override
    public BranchType findById(Integer id) {
        return this.branchTypeDAO.findById(id);
    }

    @Override
    public BranchType findByName(String name) {
        return this.branchTypeDAO.findByName(name);
    }

    @Override
    public boolean insert(BranchType item) {
        return this.branchTypeDAO.insert(item);
    }

    @Override
    public boolean update(BranchType item) {
        return this.branchTypeDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.branchTypeDAO.delete(id);
    }
}
