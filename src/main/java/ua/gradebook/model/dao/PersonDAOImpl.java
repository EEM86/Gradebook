package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO<Person> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_PERSON";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE;
    private static final String FIND_STUDENTS = "SELECT * FROM " + TABLE + " WHERE ROLE_ID=2";
    private static final String FIND_TEACHERS = "SELECT * FROM " + TABLE + " WHERE ROLE_ID=3";
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE PERSON_ID=?";
    private static final String findByNameSQL = "SELECT * FROM " + TABLE + " WHERE LAST_NAME=?";
    String findByLoginSQL = "SELECT * FROM " + TABLE + " WHERE LOGIN=?";
    private static final String FIND_NAMES = "SELECT * FROM " + TABLE + " WHERE UPPER(LAST_NAME) LIKE UPPER(?)";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE
            + " (ROLE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, ADDRESS, BIRTHDAY, DEPARTMENT_ID, CURATOR_ID, GROUP_ID, LOGIN, PASSWORD)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET  ROLE_ID=?, FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE=?, ADDRESS=?," +
            "BIRTHDAY=?, DEPARTMENT_ID=?, CURATOR_ID=?, GROUP_ID=?, LOGIN=?, PASSWORD=? WHERE PERSON_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE PERSON_ID=?";

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findStudents() {
        return jdbcTemplate.query(FIND_STUDENTS, new NewRowMapper());
    }

    @Override
    public List<Person> findTeacher() {
        return jdbcTemplate.query(FIND_TEACHERS, new NewRowMapper());
    }

    @Override
    public Person findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new NewRowMapper<Person>());
    }

    @Override
    public Person findByName(String name) {
        return jdbcTemplate.queryForObject(findByNameSQL, new Object[]{name}, new NewRowMapper<Person>());
    }

    @Override
    public Person findByLogin(String login) {
        return jdbcTemplate.queryForObject(findByLoginSQL, new Object[]{login}, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findListByObject(Object obj) {
        String corText = "%" + obj + "%";
        return jdbcTemplate.query(FIND_NAMES, new Object[]{corText}, new NewRowMapper<Person>());
    }

    @Override
    public boolean insert(Person item) {
        jdbcTemplate.update(INSERT_SQL,
                item.getRoleId(),
                item.getFirstName(),
                item.getLastName(),
                item.getEmail(),
                item.getPhone(),
                item.getAddress(),
                item.getBirthday(),
                item.getDepartmentId(),
                item.getCuratorId(),
                item.getGroupId(),
                item.getLogin(),
                item.getPassword());
        return true;
    }

    @Override
    public boolean update(Person item) {
       jdbcTemplate.update(UPDATE_SQL,
               item.getRoleId(),
               item.getFirstName(),
               item.getLastName(),
               item.getEmail(),
               item.getPhone(),
               item.getAddress(),
               item.getBirthday(),
               item.getDepartmentId(),
               item.getCuratorId(),
               item.getGroupId(),
               item.getLogin(),
               item.getPassword(),
               item.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
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
            //ToDo find how to get Integer instead of BigDecimal
            if ((resultSet.getObject(9) == null)) {
                person.setDepartmentId((Integer) resultSet.getObject(9));
            } else {
                person.setDepartmentId(Integer.valueOf(resultSet.getObject(9).toString()));
            }
            if ((resultSet.getObject(10) == null)) {
                person.setCuratorId((Integer) resultSet.getObject(10));
            } else {
                person.setCuratorId(Integer.valueOf(resultSet.getObject(10).toString()));
            }
            if ((resultSet.getObject(11) == null)) {
                person.setGroupId((Integer) resultSet.getObject(11));
            } else {
                person.setGroupId(Integer.valueOf(resultSet.getObject(11).toString()));
            }
            person.setLogin(resultSet.getString(12));
            person.setPassword(resultSet.getString(13));
            return person;
        }
    }
}
