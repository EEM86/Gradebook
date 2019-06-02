package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LessonsPlanDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_lessonsplan";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE PLAN_ID=?";
    private String insertSQL = "INSERT INTO " + table +
          " (disc_id, teacher_id, group_id, hours) VALUES (?, ?, ?, ?)";
    private String updateSQL = "UPDATE " + table +
            " SET disc_id=?, teacher_id=?, group_id=?, hours=? WHERE PLAN_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE PLAN_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (LessonsPlan) jdbcTemplate.queryForObject(findByIdSQL,
                new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        LessonsPlan lessonsPlan = (LessonsPlan) item;
        jdbcTemplate.update(insertSQL, new Object[] {
                lessonsPlan.getDisc_id(),
                lessonsPlan.getTeacher_id(),
                lessonsPlan.getGroup_id(),
                lessonsPlan.getHours()
        });
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        LessonsPlan lessonsPlan = (LessonsPlan) item;
        jdbcTemplate.update(updateSQL, new Object[]{
                lessonsPlan.getDisc_id(),
                lessonsPlan.getTeacher_id(),
                lessonsPlan.getGroup_id(),
                lessonsPlan.getHours(),
                lessonsPlan.getId()
        });
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(deleteSQL, id) == 1;
    }

    private static final class NewRowMapper<P> implements RowMapper<LessonsPlan> {

        @Override
        public LessonsPlan mapRow(ResultSet resultSet, int i) throws SQLException {
            LessonsPlan lessonsPlan = new LessonsPlan();
            lessonsPlan.setId(resultSet.getInt(1));
            lessonsPlan.setDisc_id(resultSet.getInt(2));
            lessonsPlan.setTeacher_id(resultSet.getInt(3));
            lessonsPlan.setGroup_id(resultSet.getInt(4));
            lessonsPlan.setHours(resultSet.getInt(5));
            return lessonsPlan;
        }
    }
}
