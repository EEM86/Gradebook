package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_PERSON";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE PERSON_ID=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE first_name=?";
    private String insertSQL1 = "INSERT INTO " + table + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // Add parametrs
    private String updateSQL = "UPDATE " + table + " SET PERSON_ID=?, LAST_NAME=? WHERE PERSON_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE PERSON_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (Person) jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public ParentBean findByName(String name) {
        return jdbcTemplate.queryForObject(findByNameSQL, Person.class, new NewRowMapper());
    }

    //ToDo fix code
    @Override
    public boolean insert(ParentBean item) {
//        Person person = (Person) item;
//        String insertSQL = "INSERT INTO " + table + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(
//                new PreparedStatementCreator() {
//                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                        PreparedStatement ps = connection.prepareStatement(insertSQL, new String[] {"id"});
//                        ps.setString(1, person.getFirstName());
//                        return ps;
//                    }
//                },
//                keyHolder);
//        return jdbcTemplate.update(insertSQL1, keyHolder.getKey(), person.getFirstName()) != 0;
        Person person = (Person) item;
        String insertSQL = "INSERT INTO " + table
                + "(ROLE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, ADDRESS, BIRTHDAY, DEPARTMENT_ID, CURATOR_ID, GROUP_ID, LOGIN, PASSWORD)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSQL, new Object[] {
                person.getRoleId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress(),
                person.getBirthday(),
                person.getDepartmentId(),
                person.getCuratorId(),
                person.getGroupId(),
                person.getLogin(),
                person.getPassword()
        });
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        Person person = (Person) item;
        // Add parametrs
        return jdbcTemplate.update(updateSQL, person.getId(), person.getFirstName(), person.getId()) != 0;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(deleteSQL, id) == 1;
    }

    private static final class NewRowMapper<P> implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getInt(1));
            person.setRoleId(resultSet.getInt(2));
            person.setFirstName(resultSet.getString(3));
            person.setLastName(resultSet.getString(4));
            person.setEmail(resultSet.getString(5));
            person.setPhone(resultSet.getString(6));
            person.setAddress(resultSet.getString(7));
            person.setBirthday(resultSet.getDate(8));
            person.setDepartmentId(resultSet.getInt(9));
            person.setCuratorId(resultSet.getInt(10));
            person.setGroupId(resultSet.getInt(11));
            person.setLogin(resultSet.getString(12));
            person.setPassword(resultSet.getString(13));
            return person;
        }
    }
}
