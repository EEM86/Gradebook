package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DAOExtension;
import ua.gradebook.model.dao.GradesJournalDAOImpl;

import java.util.List;

@Service
public class JournalService implements AppServiceExtension<GradesJournal> {

    private final DAOExtension<GradesJournal> journalDAO;

    @Autowired
    public JournalService(GradesJournalDAOImpl journalDAO) {
        this.journalDAO = journalDAO;
    }

    @Override
    public List<GradesJournal> findAll() {
        return this.journalDAO.findAll();
    }

    @Override
    public GradesJournal findById(Integer id) {
        return this.journalDAO.findById(id);
    }

    @Override
    public GradesJournal findByName(String name) {
        return this.journalDAO.findByName(name);
    }

    @Override
    public boolean insert(GradesJournal item) {
        return this.journalDAO.insert(item);
    }

    @Override
    public boolean update(GradesJournal item) {
        return this.journalDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.journalDAO.delete(id);
    }

    @Override
    public List<GradesJournal> findListByObject(Object id) {
        return this.journalDAO.findListByObject(id);
    }

    @Override
    public GradesJournal findByLogin(String login) {
        return null;
    }
}
