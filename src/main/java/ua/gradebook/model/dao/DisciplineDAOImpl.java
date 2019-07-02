package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Discipline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DisciplineDAOImpl implements DAO<Discipline> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_DISCIPLINE";
    private static final String FIND_All = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE DISC_ID=?";
    private static final String FIND_BY_NAME = "SELECT * FROM " + TABLE + " WHERE name=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " (DISC_NAME) VALUES (?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET DISC_NAME=? WHERE DISC_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE DISC_ID=?";

    @Override
    public List<Discipline> findAll() {
        return jdbcTemplate.query(FIND_All, new NewRowMapper<Discipline>());
    }

    @Override
    public Discipline findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new NewRowMapper<Discipline>());
    }

    @Override
    public Discipline findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME, new Object[]{name}, new NewRowMapper<Discipline>());
    }

    @Override
    public boolean insert(Discipline item) {
        return (jdbcTemplate.update(INSERT_SQL, item.getDiscName()) == 1);
    }

    @Override
    public boolean update(Discipline item) {
        return jdbcTemplate.update(UPDATE_SQL, item.getDiscName(), item.getId()) == 1;
    }

    @Override
    public boolean delete(int id) {
        return (jdbcTemplate.update(DELETE_SQL, id) == 1);
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
