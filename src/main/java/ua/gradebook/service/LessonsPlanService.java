package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.DAOExtension;

import java.util.List;

@Service(value="LessonsPlanService")
public class LessonsPlanService implements AppServiceExtension {
    @Autowired
    @Qualifier("LessonsPlanDAO")
    private DAOExtension lessonsPlanDAO;

    @Override
    public List findAll() {
        return this.lessonsPlanDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.lessonsPlanDAO.findById(id);
    }

    @Override
    public ParentBean findByName(String name) {
        return this.lessonsPlanDAO.findByName(name);
    }

    @Override
    public boolean insert(ParentBean item) {
        return this.lessonsPlanDAO.insert(item);
    }

    @Override
    public boolean update(ParentBean item) {
        return this.lessonsPlanDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.lessonsPlanDAO.delete(id);
    }

    @Override
    public List<ParentBean> findListByObject(Object id) {
        return this.lessonsPlanDAO.findListByObject(id);
    }

    @Override
    public ParentBean findByLogin(String login) {
        return null;
    }
}
