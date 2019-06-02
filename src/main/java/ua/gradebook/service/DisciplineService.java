package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DisciplineDAOImpl;

import java.util.List;

@Service
public class DisciplineService implements AppService {
    @Autowired
    DisciplineDAOImpl disciplineDAO;

    @Override
    public List findAll() {
        return this.disciplineDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.disciplineDAO.findById(id);
    }

    @Override
    public ParentBean findByName(String name) {
        return this.disciplineDAO.findByName(name);
    }

    @Override
    public boolean insert(ParentBean item) {
        return this.disciplineDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.disciplineDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.disciplineDAO.delete(id);
    }
}
