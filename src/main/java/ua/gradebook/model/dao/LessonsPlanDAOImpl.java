package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.Discipline;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LessonsPlanDAOImpl implements DAOExtension<LessonsPlan> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_lessonsplan";
    private static final String FIND_ALL =
            "SELECT l.ID, l.DISC_ID, d.DISC_NAME, l.TEACHER_ID, p.FIRST_NAME, p.LAST_NAME, l.GROUP_ID,  c.NAME, l.HOURS FROM " + TABLE + " l " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON l.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON l.TEACHER_ID = p.ID " +
                    "LEFT JOIN L3G3_CONTAINER c ON l.GROUP_ID = c.ID";

    private static final String FIND_BY_ID =
            "SELECT l.ID, l.DISC_ID, d.DISC_NAME, l.TEACHER_ID, p.FIRST_NAME, p.LAST_NAME, l.GROUP_ID,  c.NAME, l.HOURS FROM " + TABLE + " l " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON l.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON l.TEACHER_ID = p.ID " +
                    "LEFT JOIN L3G3_CONTAINER c ON l.GROUP_ID = c.ID " +
                    "WHERE l.ID=?";

    private static final String FIND_RELATIVE_PLAN_BY_ID =
            "SELECT l.ID, l.DISC_ID, d.DISC_NAME, l.TEACHER_ID, p.FIRST_NAME, p.LAST_NAME, l.GROUP_ID,  c.NAME, l.HOURS FROM " + TABLE + " l " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON l.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON l.TEACHER_ID = p.ID " +
                    "LEFT JOIN L3G3_CONTAINER c ON l.GROUP_ID = c.ID " +
                    "WHERE l.TEACHER_ID=?";

    private static final String INSERT_SQL = "INSERT INTO " + TABLE +
            " (DISC_ID, TEACHER_ID, GROUP_ID, HOURS) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE +
            " SET DISC_ID=?, TEACHER_ID=?, GROUP_ID=?, HOURS=? WHERE ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE ID=?";

    @Override
    public List<LessonsPlan> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<LessonsPlan>());
    }

    @Override
    public LessonsPlan findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                new Object[]{id}, new NewRowMapper<LessonsPlan>());
    }

    @Override
    public LessonsPlan findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(LessonsPlan item) {
        jdbcTemplate.update(INSERT_SQL,
                item.getDiscipline().getId(),
                item.getTeacher().getId(),
                item.getGroup().getId(),
                item.getHours());
        return true;
    }

    @Override
    public boolean update(LessonsPlan item) {
        jdbcTemplate.update(UPDATE_SQL,
                item.getDiscipline().getId(),
                item.getTeacher().getId(),
                item.getGroup().getId(),
                item.getHours(),
                item.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    @Override
    public List<LessonsPlan> findListByObject(Object obj) {
        return jdbcTemplate.query(FIND_RELATIVE_PLAN_BY_ID, new Object[]{obj}, new NewRowMapper<LessonsPlan>());
    }

    private static final class NewRowMapper<P> implements RowMapper<LessonsPlan> {

        @Override
        public LessonsPlan mapRow(ResultSet resultSet, int i) throws SQLException {
            Discipline discipline = new Discipline();
            discipline.setId(resultSet.getInt(2));
            discipline.setDiscName(resultSet.getString(3));

            Person teacher = new Person();
            teacher.setId(resultSet.getInt(4));
            teacher.setFirstName(resultSet.getString(5));
            teacher.setLastName(resultSet.getString(6));

            Container container = new Container();
            container.setId(resultSet.getInt(7));
            container.setName(resultSet.getString(8));

            LessonsPlan lessonsPlan = (new BeanPropertyRowMapper<>(LessonsPlan.class)).mapRow(resultSet, i);
            lessonsPlan.setDiscipline(discipline);
            lessonsPlan.setGroup(container);
            lessonsPlan.setTeacher(teacher);
            return lessonsPlan;
        }
    }
}
