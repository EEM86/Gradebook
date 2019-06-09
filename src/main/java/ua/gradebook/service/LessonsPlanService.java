package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.dao.LessonsPlanDAOImpl;

import java.util.List;

@Service
public class LessonsPlanService implements AppService {
    @Autowired
    LessonsPlanDAOImpl lessonsPlanDAO;

    @Override
    public List findAll() {
        return this.lessonsPlanDAO.findAll();
    }

    @Override
    public ParentBean findById(Integer id) {
        return this.lessonsPlanDAO.findById(id);
    }

    public List<ParentBean> findRelativePlanById(Integer id) {
        return this.lessonsPlanDAO.findRelativePlanById(id);
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
}
