package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Discipline;
import ua.gradebook.model.dao.DAO;
import ua.gradebook.model.dao.DisciplineDAOImpl;

import java.util.List;

@Service
public class DisciplineService implements AppService<Discipline> {

    private final DAO<Discipline> disciplineDAO;

    @Autowired
    public DisciplineService(DisciplineDAOImpl disciplineDAO) {
        this.disciplineDAO = disciplineDAO;
    }

    @Override
    public List findAll() {
        return this.disciplineDAO.findAll();
    }

    @Override
    public Discipline findById(Integer id) {
        return this.disciplineDAO.findById(id);
    }

    @Override
    public Discipline findByName(String name) {
        return this.disciplineDAO.findByName(name);
    }

    @Override
    public boolean insert(Discipline item) {
        return this.disciplineDAO.insert(item);
    }

    @Override
    public boolean update(Discipline item) {
        return this.disciplineDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.disciplineDAO.delete(id);
    }
}
