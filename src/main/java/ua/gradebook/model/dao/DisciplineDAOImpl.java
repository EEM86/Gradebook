package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Discipline;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DisciplineDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_DISCIPLINE";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE DISC_ID=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL = "INSERT INTO " + table + " (DISC_NAME) VALUES (?)";
    private String updateSQL = "UPDATE " + table + " SET DISC_NAME=? WHERE DISC_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE DISC_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (Discipline) jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return (Discipline) jdbcTemplate.queryForObject(findByNameSQL, new Object[]{name}, new NewRowMapper());
    }

    @Override
    public boolean insert(ParentBean item) {
        Discipline discipline = (Discipline) item;
        return (jdbcTemplate.update(insertSQL, discipline.getDiscName()) == 1);
    }

    @Override
    public boolean update(ParentBean item) {
        Discipline discipline = (Discipline) item;
        return jdbcTemplate.update(updateSQL, discipline.getDiscName(), discipline.getId()) == 1;
    }

    @Override
    public boolean delete(int id) {
        return (jdbcTemplate.update(deleteSQL, id) == 1);
    }

    private static final class NewRowMapper<P> implements RowMapper<Discipline> {

        @Override
        public Discipline mapRow(ResultSet resultSet, int i) throws SQLException {
            Discipline discipline = new Discipline();
            discipline.setId(resultSet.getInt(1));
            discipline.setDiscName(resultSet.getString(2));
            return discipline;
        }
    }
}
