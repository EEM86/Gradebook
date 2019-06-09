package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_ROLE";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE ROLE_ID=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE ROLE_NAME=?";
    private String insertSQL1 = "INSERT INTO " + table + " (ROLE_NAME) VALUES (?)";
    private String updateSQL = "UPDATE " + table + " SET ROLE_NAME=? WHERE ROLE_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE ROLE_ID=?";
    private String findNextSQL = "SELECT id.nextval as Id from dual";
    private String insertSQL = "INSERT into " + table + " (ROLE_NAME)" + "VALUES(?)";

    public RoleDAOImpl() {
    }

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (Role) jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return (Role) jdbcTemplate.queryForObject(findByNameSQL, new Object[]{name}, new NewRowMapper());
    }

    @Override
    public boolean insert(ParentBean item) {
        Role role = (Role) item;
        return (jdbcTemplate.update(insertSQL, role.getRoleName()) == 1);
    }

    @Override
    public boolean update(ParentBean item) {
        Role role = (Role) item;
        return jdbcTemplate.update(updateSQL, role.getRoleName(), role.getId()) != 0;
    }

    @Override
    public boolean delete(int id) {
        return (jdbcTemplate.update(deleteSQL, id) == 1);
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
