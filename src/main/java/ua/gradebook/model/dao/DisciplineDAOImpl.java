package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Discipline;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DisciplineDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_discipline";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE id=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL1 = "INSERT INTO " + table + " (id, name) VALUES (?, ?)";
    private String updateSQL = "UPDATE " + table + " SET id=?, name=? WHERE id=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE id=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Long id) {
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

    private static final class NewRowMapper<P> implements RowMapper<Discipline> {

        @Override
        public Discipline mapRow(ResultSet resultSet, int i) throws SQLException {
            Discipline discipline = new Discipline();
            discipline.setId(resultSet.getInt(1));
            discipline.setName(resultSet.getString(2));
            return discipline;
        }
    }
}
