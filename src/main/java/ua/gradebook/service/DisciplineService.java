package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DAO;

import java.util.List;

@Service(value="DisciplineService")
public class DisciplineService implements AppService {
    @Autowired
    @Qualifier("DisciplineDAO")
    private DAO disciplineDAO;

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
