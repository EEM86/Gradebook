package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GradesJournalDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_gradesjournal";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE JOURNAL_ID=?";
    private String insertSQL = "INSERT INTO " + table + " (DISC_ID, PERSON_ID, GRADE, TEACHER_ID)"
           + " VALUES (?, ?, ?, ?)";
    private String updateSQL = "UPDATE " + table + " SET DISC_ID=?, PERSON_ID=?, GRADE=?, TEACHER_ID=? WHERE JOURNAL_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE JOURNAL_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (GradesJournal) jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        GradesJournal gradesJournal = (GradesJournal) item;
        jdbcTemplate.update(insertSQL, new Object[] {
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
        jdbcTemplate.update(updateSQL, new Object[]{
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
        return jdbcTemplate.update(deleteSQL, id) == 1;
    }

    private static final class NewRowMapper<P> implements RowMapper<GradesJournal> {

        @Override
        public GradesJournal mapRow(ResultSet resultSet, int i) throws SQLException {
            GradesJournal gradesJournal = new GradesJournal();
            gradesJournal.setId(resultSet.getInt(1));
            gradesJournal.setDiscId(resultSet.getInt(2));
            gradesJournal.setPersonId(resultSet.getInt(3));
            gradesJournal.setGrade(resultSet.getInt(4));
            gradesJournal.setTeacherId(resultSet.getInt(5));
            return gradesJournal;
        }
    }
}
