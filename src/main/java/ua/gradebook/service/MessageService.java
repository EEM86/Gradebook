package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DAOExtension;

import java.util.List;

@Service(value="MessageService")
public class MessageService implements AppServiceExtension {
    @Autowired
    @Qualifier("MessageDAO")
    DAOExtension messageDAO;

    @Override
    public List<ParentBean> findAll() {
        return this.messageDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.messageDAO.findById(id);
    }

    @Override
    public ParentBean findByName(String name) {
        return this.messageDAO.findByName(name);
    }

    @Override
    public List<ParentBean> findListByObject(Object id) {
        return this.messageDAO.findListByObject(id);
    }

    @Override
    public ParentBean findByLogin(String login) { return null; }

    @Override
    public boolean insert(ParentBean item) {
        return this.messageDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.messageDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.messageDAO.delete(id);
    }
}
