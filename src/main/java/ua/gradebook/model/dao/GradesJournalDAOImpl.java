package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value="GradesJournalDAO")
public class GradesJournalDAOImpl implements DAOExtension {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_gradesjournal";
    private static final String FIND_ALL = "SELECT j.JOURNAL_ID, j.DISC_ID, d.DISC_NAME, j.PERSON_ID, concat(concat(p.FIRST_NAME, ' '), p.LAST_NAME) as StudName, " +
            "j.GRADE, j.TEACHER_ID, concat(concat(p2.FIRST_NAME, ' '), p2.LAST_NAME) as TeachName FROM " + TABLE + " j " +
            "LEFT JOIN L3G3_DISCIPLINE d ON j.DISC_ID = d.DISC_ID " +
            "LEFT JOIN L3G3_PERSON p ON j.PERSON_ID = p.PERSON_ID " +
            "LEFT JOIN L3G3_PERSON p2 ON j.TEACHER_ID = p2.PERSON_ID";
//    private static final String FIND_ALL = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE JOURNAL_ID=?";
    private static final String FIND_RELATIVE_DATA_BY_ID = "SELECT * FROM " + TABLE + " WHERE PERSON_ID=? OR TEACHER_ID=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " (DISC_ID, PERSON_ID, GRADE, TEACHER_ID)"
           + " VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET DISC_ID=?, PERSON_ID=?, GRADE=?, TEACHER_ID=? WHERE JOURNAL_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE JOURNAL_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new NewRowMapper<GradesJournal>());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        GradesJournal gradesJournal = (GradesJournal) item;
        jdbcTemplate.update(INSERT_SQL, new Object[] {
                gradesJournal.getDiscId(),
                gradesJournal.getPersonId(),
                gradesJournal.getGrade(),
                gradesJournal.getTeacherId()
        });
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        GradesJournal gradesJournal = (GradesJournal) item;
        jdbcTemplate.update(UPDATE_SQL, new Object[]{
                gradesJournal.getDiscId(),
                gradesJournal.getPersonId(),
                gradesJournal.getGrade(),
                gradesJournal.getTeacherId(),
                gradesJournal.getId()
        });
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    @Override
    public List<ParentBean> findListByObject(Object id) {
        return (List) jdbcTemplate.query(FIND_RELATIVE_DATA_BY_ID, new Object[]{id, id}, new NewRowMapper<>());
    }

    @Override
    public ParentBean findByLogin(String login) {
        return null;
    }

    private static final class NewRowMapper<P> implements RowMapper<GradesJournal> {

        @Override
        public GradesJournal mapRow(ResultSet resultSet, int i) throws SQLException {
            GradesJournal gradesJournal = new GradesJournal();
            gradesJournal.setId(resultSet.getInt(1));
            gradesJournal.setDiscId(resultSet.getInt(2));
            gradesJournal.setDiscName(resultSet.getString(3));
            gradesJournal.setPersonId(resultSet.getInt(4));
            gradesJournal.setStudentName(resultSet.getString(5));
            gradesJournal.setGrade(resultSet.getInt(6));
            gradesJournal.setTeacherId(resultSet.getInt(7));
            gradesJournal.setTeacherName(resultSet.getString(8));
            return gradesJournal;
        }
    }
}
