package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value="LessonsPlanDAO")
public class LessonsPlanDAOImpl implements DAOExtension {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_lessonsplan";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE PLAN_ID=?";
    private static final String FIND_RELATIVE_PLAN_BY_ID = "SELECT * FROM " + TABLE + " WHERE TEACHER_ID=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE +
          " (DISC_ID, TEACHER_ID, GROUP_ID, HOURS) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE +
            " SET DISC_ID=?, TEACHER_ID=?, GROUP_ID=?, HOURS=? WHERE PLAN_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE PLAN_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                new Object[]{id}, new NewRowMapper<LessonsPlan>());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        LessonsPlan lessonsPlan = (LessonsPlan) item;
        jdbcTemplate.update(INSERT_SQL, new Object[] {
                lessonsPlan.getDiscId(),
                lessonsPlan.getTeacherId(),
                lessonsPlan.getGroupId(),
                lessonsPlan.getHours()
        });
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        LessonsPlan lessonsPlan = (LessonsPlan) item;
        jdbcTemplate.update(UPDATE_SQL, new Object[]{
                lessonsPlan.getDiscId(),
                lessonsPlan.getTeacherId(),
                lessonsPlan.getGroupId(),
                lessonsPlan.getHours(),
                lessonsPlan.getId()
        });
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    @Override
    public List<ParentBean> findListByObject(Object id) {
        return (List) jdbcTemplate.query(FIND_RELATIVE_PLAN_BY_ID, new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByLogin(String login) {
        return null;
    }

    private static final class NewRowMapper<P> implements RowMapper<LessonsPlan> {

        @Override
        public LessonsPlan mapRow(ResultSet resultSet, int i) throws SQLException {
            LessonsPlan lessonsPlan = new LessonsPlan();
            lessonsPlan.setId(resultSet.getInt(1));
            lessonsPlan.setDiscId(resultSet.getInt(2));
            lessonsPlan.setTeacherId(resultSet.getInt(3));
            lessonsPlan.setGroupId(resultSet.getInt(4));
            lessonsPlan.setHours(resultSet.getInt(5));
            return lessonsPlan;
        }
    }
}
