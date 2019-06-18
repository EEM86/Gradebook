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
    private static final String FIND_ALL =
            "SELECT l.PLAN_ID, l.DISC_ID, d.DISC_NAME, l.TEACHER_ID, concat(concat(p.FIRST_NAME, ' '), p.LAST_NAME), l.GROUP_ID,  c.NAME, l.HOURS FROM " + TABLE + " l " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON l.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON l.TEACHER_ID = p.PERSON_ID " +
                    "LEFT JOIN L3G3_CONTAINER c ON l.GROUP_ID = c.ID";

    private static final String FIND_BY_ID =
            "SELECT l.PLAN_ID, l.DISC_ID, d.DISC_NAME, l.TEACHER_ID, concat(concat(p.FIRST_NAME, ' '), p.LAST_NAME), l.GROUP_ID,  c.NAME, l.HOURS FROM " + TABLE + " l " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON l.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON l.TEACHER_ID = p.PERSON_ID " +
                    "LEFT JOIN L3G3_CONTAINER c ON l.GROUP_ID = c.ID " +
                    "WHERE l.PLAN_ID=?";

    private static final String FIND_RELATIVE_PLAN_BY_ID =
            "SELECT l.PLAN_ID, l.DISC_ID, d.DISC_NAME, l.TEACHER_ID, concat(concat(p.FIRST_NAME, ' '), p.LAST_NAME), l.GROUP_ID,  c.NAME, l.HOURS FROM " + TABLE + " l " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON l.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON l.TEACHER_ID = p.PERSON_ID " +
                    "LEFT JOIN L3G3_CONTAINER c ON l.GROUP_ID = c.ID " +
                    "WHERE l.TEACHER_ID=?";

    private static final String INSERT_SQL =
            "INSERT INTO " + TABLE + " (DISC_ID, TEACHER_ID, GROUP_ID, HOURS) " +
                "VALUES (" +
                    "(SELECT DISC_ID FROM L3G3_DISCIPLINE WHERE UPPER(DISC_NAME)=UPPER(?)), " +
                    "(SELECT PERSON_ID FROM L3G3_PERSON WHERE UPPER(CONCAT(CONCAT(FIRST_NAME, ' '), LAST_NAME)) = UPPER(?)), " +
                    "(SELECT ID FROM L3G3_CONTAINER WHERE UPPER(NAME) = UPPER(?)), " +
                    "?)";

    private static final String UPDATE_SQL =
            "UPDATE " + TABLE + " SET " +
                    "DISC_ID=(SELECT DISC_ID FROM L3G3_DISCIPLINE WHERE UPPER(DISC_NAME)=UPPER(?)), " +
                    "TEACHER_ID=(SELECT PERSON_ID FROM L3G3_PERSON WHERE UPPER(CONCAT(CONCAT(FIRST_NAME, ' '), LAST_NAME)) = UPPER(?)), " +
                    "GROUP_ID=(SELECT ID FROM L3G3_CONTAINER WHERE UPPER(NAME) = UPPER(?)), " +
                    "HOURS=?" +
                    "WHERE PLAN_ID=?";
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
        jdbcTemplate.update(INSERT_SQL,
                lessonsPlan.getDiscName(),
                lessonsPlan.getTeacherName(),
                lessonsPlan.getGroupName(),
                lessonsPlan.getHours());
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        LessonsPlan lessonsPlan = (LessonsPlan) item;
        jdbcTemplate.update(UPDATE_SQL,
                lessonsPlan.getDiscName(),
                lessonsPlan.getTeacherName(),
                lessonsPlan.getGroupName(),
                lessonsPlan.getHours(),
                lessonsPlan.getId());
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

    private static final class NewRowMapper<P> implements RowMapper<LessonsPlan> {

        @Override
        public LessonsPlan mapRow(ResultSet resultSet, int i) throws SQLException {
            LessonsPlan lessonsPlan = new LessonsPlan();
            lessonsPlan.setId(resultSet.getInt(1));
            lessonsPlan.setDiscId(resultSet.getInt(2));
            lessonsPlan.setDiscName(resultSet.getString(3));
            lessonsPlan.setTeacherId(resultSet.getInt(4));
            lessonsPlan.setTeacherName(resultSet.getString(5));
            lessonsPlan.setGroupId(resultSet.getInt(6));
            lessonsPlan.setGroupName(resultSet.getString(7));
            lessonsPlan.setHours(resultSet.getInt(8));
            return lessonsPlan;
        }
    }
}
