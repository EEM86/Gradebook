package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.MessageDAOImpl;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDAOImpl messageDAO;

    public List<ParentBean> findAll() {
        return this.messageDAO.findAll();
    }

    public ParentBean findById(Integer id) {
        return this.messageDAO.findById(id);
    }

    public ParentBean findByName(String name) {
        return this.messageDAO.findByName(name);
    }

    public boolean insert(ParentBean item) {
        return this.messageDAO.insert(item);
    }

    public boolean update(ParentBean item) {
        return this.messageDAO.update(item);
    }

    public boolean delete(int id) {
        return this.messageDAO.delete(id);
    }

}