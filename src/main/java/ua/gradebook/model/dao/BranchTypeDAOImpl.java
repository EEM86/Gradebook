package ua.gradebook.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;

@Repository
public class BranchTypeDAOImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

/*    private String table = "L3G3_branch_types";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE id=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL1 = "INSERT INTO " + table + " (id, name) VALUES (?, ?)";
    private String updateSQL = "UPDATE " + table + " SET id=?, name=? WHERE id=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE id=?";

    public BranchTypeDAOImpl() {
    }

    public List<Person> getPerson() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }
*//*    public List<Role> getRoles() {

        return jdbcTemplate.query("SELECT * FROM ROLES", new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                Role roles = new Role(resultSet.getString(1));
                System.out.println(roles.toString());
                return roles;
            }
        });
    }*//*

    @Override
    public List<ParentBean> findAll() {
        return null;
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


    private static final class NewRowMapper<P> implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getInt(1));
            person.setRole(resultSet.getInt(2));





            return person;
        }*/
}
