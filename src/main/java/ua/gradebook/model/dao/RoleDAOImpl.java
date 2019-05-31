package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RoleDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_role";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE id=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL1 = "INSERT INTO " + table + " (id, name) VALUES (?, ?)";
    private String updateSQL = "UPDATE " + table + " SET id=?, name=? WHERE id=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE id=?";
    private String findNextSQL = "SELECT id.nextval as Id from dual";
    private String CREATE_SQL = "insert into L3G3_role (name) values(:name)";

    public RoleDAOImpl() {
    }

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Long id) {
        return jdbcTemplate.queryForObject(findByIdSQL, Role.class, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return jdbcTemplate.queryForObject(findByNameSQL, Role.class, new NewRowMapper());
    }

    @Override
    public boolean insert(ParentBean item) {
        Role role = (Role) item;
        String insertSQL = "INSERT INTO " + table + " (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(insertSQL, new String[] {"id"});
                        ps.setString(1, role.getRole());
                        return ps;
                    }
                },
                keyHolder);
        return jdbcTemplate.update(insertSQL1, keyHolder.getKey(), role.getRole()) != 0;
    }

    @Override
    public boolean update(ParentBean item) {
        Role role = (Role) item;
        return jdbcTemplate.update(updateSQL, role.getId(), role.getRole(), role.getId()) != 0;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(deleteSQL, id) != 0;
    }

    @Override
    public Long getNextId() {
        String sql = "SELECT L3G3_role.id.nextval as id from dual";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
/*                ps.setLong(1, i);
                ps.setLong(2, parameterID);*/
            }
        }, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }
                return null;
            }
        });
    }

    private static final class NewRowMapper<P> implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setId(resultSet.getInt(1));
            role.setRole(resultSet.getString(2));
            return role;
        }
    }
}
