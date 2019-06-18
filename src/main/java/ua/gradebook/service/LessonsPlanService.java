package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.dao.DAOExtension;
import ua.gradebook.model.dao.LessonsPlanDAOImpl;

import java.util.List;

@Service
public class LessonsPlanService implements AppServiceExtension<LessonsPlan> {

    private final DAOExtension<LessonsPlan> lessonsPlanDAO;

    @Autowired
    public LessonsPlanService(LessonsPlanDAOImpl lessonsPlanDAO) {
        this.lessonsPlanDAO = lessonsPlanDAO;
    }

    @Override
    public List<LessonsPlan> findAll() {
        return this.lessonsPlanDAO.findAll();
    }

    @Override
    public LessonsPlan findById(Integer id) {
        return this.lessonsPlanDAO.findById(id);
    }

    @Override
    public LessonsPlan findByName(String name) {
        return this.lessonsPlanDAO.findByName(name);
    }

    @Override
    public boolean insert(LessonsPlan item) {
        return this.lessonsPlanDAO.insert(item);
    }

    @Override
    public boolean update(LessonsPlan item) {
        return this.lessonsPlanDAO.update(item);
    }

    @Override
    public boolean delete(int id) {
        return this.lessonsPlanDAO.delete(id);
    }

    @Override
    public List<LessonsPlan> findListByObject(Object id) {
        return this.lessonsPlanDAO.findListByObject(id);
    }
}
