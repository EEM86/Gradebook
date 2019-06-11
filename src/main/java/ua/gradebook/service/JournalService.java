package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DAOExtension;

import java.util.List;

@Service(value="JournalService")
public class JournalService implements AppServiceExtension {
    @Autowired
    @Qualifier("GradesJournalDAO")
    DAOExtension journalDAO;

    @Override
    public List findAll() {
        return this.journalDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.journalDAO.findById(id);
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

    @Override
    public List<ParentBean> findListByObject(Object id) {
        return this.journalDAO.findListByObject(id);
    }

    @Override
    public ParentBean findByLogin(String login) {
        return null;
    }
}
