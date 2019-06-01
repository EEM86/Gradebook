package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.GradesJournal;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class GradesJournalDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_gradesjournal";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE id=?";
   // private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL1 = "INSERT INTO " + table + " (id, name) VALUES (?, ?)";
    private String updateSQL = "UPDATE " + table + " SET id=?, name=? WHERE id=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE id=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return null;
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        return false;
    }

    @Override
    public boolean update(ParentBean item) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Long getNextId() {
        return null;
    }

    private static final class NewRowMapper<P> implements RowMapper<GradesJournal> {

        @Override
        public GradesJournal mapRow(ResultSet resultSet, int i) throws SQLException {
            GradesJournal gradesJournal = new GradesJournal();
            gradesJournal.setId(resultSet.getInt(1));
            gradesJournal.setDiscipline(resultSet.getInt(2));
            gradesJournal.setPerson_id(resultSet.getInt(3));
            gradesJournal.setGrade(resultSet.getInt(4));
            gradesJournal.setTeacher_id(resultSet.getInt(5));
            return gradesJournal;
        }
    }
}
