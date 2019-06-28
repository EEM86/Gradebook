package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDAOImpl implements DAO<Role> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_ROLE";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE ROLE_ID=?";
    private static final String findByNameSQL = "SELECT * FROM " + TABLE + " WHERE ROLE_NAME=?";
    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET ROLE_NAME=? WHERE ROLE_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE ROLE_ID=?";
    private static final String INSERT_SQL = "INSERT into " + TABLE + " (ROLE_NAME)" + "VALUES(?)";

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<Role>());
    }

    @Override
    public Role findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new NewRowMapper<Role>());
    }

    @Override
    public Role findByName(String name) {
        return jdbcTemplate.queryForObject(findByNameSQL, new Object[]{name}, new NewRowMapper<Role>());
    }

    @Override
    public boolean insert(Role item) {
        return (jdbcTemplate.update(INSERT_SQL, item.getRoleName()) == 1);
    }

    @Override
    public boolean update(Role item) {
        return jdbcTemplate.update(UPDATE_SQL, item.getRoleName(), item.getId()) != 0;
    }

    @Override
    public boolean delete(int id) {
        return (jdbcTemplate.update(DELETE_SQL, id) == 1);
    }

    private static final class NewRowMapper<P> implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setId(resultSet.getInt(1));
            role.setRoleName(resultSet.getString(2));
            return role;
        }
    }
}
