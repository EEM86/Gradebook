package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.dao.DAOExtension;
import ua.gradebook.model.dao.MessageDAOImpl;

import java.util.List;

@Service
public class MessageService implements AppServiceExtension<Message> {
    private final DAOExtension<Message> messageDAO;

    @Autowired
    public MessageService(MessageDAOImpl messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public List<Message> findAll() {
        return this.messageDAO.findAll();
    }

    @Override
    public Message findById(Integer id) {
        return this.messageDAO.findById(id);
    }

    @Override
    public Message findByName(String name) {
        return this.messageDAO.findByName(name);
    }

    @Override
    public List<Message> findListByObject(Object id) {
        return this.messageDAO.findListByObject(id);
    }

    @Override
    public Message findByLogin(String login) { return null; }

    @Override
    public boolean insert(Message item) {
        return this.messageDAO.insert(item);
    }

    @Override
    public boolean update(Message item) {
        return this.messageDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.messageDAO.delete(id);
    }
}
