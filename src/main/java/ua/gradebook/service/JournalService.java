package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.GradesJournalDAOImpl;

import java.util.List;

@Service
public class JournalService implements AppService {
    @Autowired
    GradesJournalDAOImpl journalDAO;

    @Override
    public List findAll() {
        return this.journalDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.journalDAO.findById(id);
    }

    public List<ParentBean> findRelativeDataById(Integer id) {
        return this.journalDAO.findRelativeDataById(id);
    }

    @Override
    public ParentBean findByName(String name) {
        return this.journalDAO.findByName(name);
    }

    @Override
    public boolean insert(ParentBean item) {
        return this.journalDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.journalDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.journalDAO.delete(id);
    }

}
