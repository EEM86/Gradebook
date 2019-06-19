package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Discipline;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GradesJournalDAOImpl implements DAOExtension<GradesJournal> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_GRADESJOURNAL";
    private static final String FIND_ALL =
        "SELECT j.ID, d.*, p.ID, p.FIRST_NAME, p.LAST_NAME, j.GRADE, p2.ID, p2.FIRST_NAME, p2.LAST_NAME FROM " + TABLE + " j " +
            "LEFT JOIN L3G3_DISCIPLINE d ON j.DISC_ID = d.DISC_ID " +
            "LEFT JOIN L3G3_PERSON p ON j.STUDENT_ID = p.ID " +
            "LEFT JOIN L3G3_PERSON p2 ON j.TEACHER_ID = p2.ID";
    private static final String FIND_BY_ID =
            "SELECT j.ID, d.*, p.ID, p.FIRST_NAME, p.LAST_NAME, j.GRADE, p2.ID, p2.FIRST_NAME, p2.LAST_NAME FROM " + TABLE + " j " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON j.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON j.STUDENT_ID = p.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON j.TEACHER_ID = p2.ID " +
            "WHERE j.ID=?";
    private static final String FIND_RELATIVE_DATA_BY_ID =
            "SELECT j.ID, d.*, p.ID, p.FIRST_NAME, p.LAST_NAME, j.GRADE, p2.ID, p2.FIRST_NAME, p2.LAST_NAME FROM " + TABLE + " j " +
                    "LEFT JOIN L3G3_DISCIPLINE d ON j.DISC_ID = d.DISC_ID " +
                    "LEFT JOIN L3G3_PERSON p ON j.STUDENT_ID = p.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON j.TEACHER_ID = p2.ID " +
            "WHERE j.STUDENT_ID=? OR j.TEACHER_ID=?";

    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " (DISC_ID, STUDENT_ID, GRADE, TEACHER_ID)"
             + " VALUES (?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET DISC_ID=?, STUDENT_ID=?, GRADE=?, TEACHER_ID=? WHERE ID=?";

    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE ID=?";

    @Override
    public List<GradesJournal> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<GradesJournal>());
    }

    @Override
    public GradesJournal findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new NewRowMapper<GradesJournal>());
    }

    @Override
    public GradesJournal findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(GradesJournal item) {
        jdbcTemplate.update(INSERT_SQL,
                item.getDiscipline().getId(),
                item.getStudent().getId(),
                item.getGrade(),
                item.getTeacher().getId());
        return true;
}

    @Override
    public boolean update(GradesJournal item) {
        jdbcTemplate.update(UPDATE_SQL,
                item.getDiscipline().getId(),
                item.getStudent().getId(),
                item.getGrade(),
                item.getTeacher().getId(),
                item.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    @Override
    public List<GradesJournal> findListByObject(Object id) {
        return jdbcTemplate.query(FIND_RELATIVE_DATA_BY_ID, new Object[]{id, id}, new NewRowMapper<>());
    }

    private static final class NewRowMapper<P> implements RowMapper<GradesJournal> {

        @Override
        public GradesJournal mapRow(ResultSet resultSet, int i) throws SQLException {
            Discipline discipline = new Discipline();
            discipline.setId(resultSet.getInt(2));
            discipline.setDiscName(resultSet.getString(3));
            Person student = new Person();
            student.setId(resultSet.getInt(4));
            student.setFirstName(resultSet.getString(5));
            student.setLastName(resultSet.getString(6));
            Integer grade = resultSet.getInt(7);

            Person teacher = new Person();
            teacher.setId(resultSet.getInt(8));
            teacher.setFirstName(resultSet.getString(9));
            teacher.setLastName(resultSet.getString(10));

            GradesJournal gradesJournal = new GradesJournal();
            gradesJournal.setId(resultSet.getInt(1));
            gradesJournal.setStudent(student);
            gradesJournal.setDiscipline(discipline);
            gradesJournal.setGrade(grade);
            gradesJournal.setTeacher(teacher);
            return gradesJournal;
        }
    }
}
