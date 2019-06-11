package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.ParentBean;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value="PersonDAO")
public class PersonDAOImpl implements DAOExtension {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_PERSON";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE PERSON_ID=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE LAST_NAME=?";
    String findByLoginSQL = "SELECT * FROM " + table + " WHERE LOGIN=?";
    private String findNamesSQL = "SELECT * FROM " + table + " WHERE LAST_NAME LIKE ?";
    private String insertSQL = "INSERT INTO " + table
            + " (ROLE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, ADDRESS, BIRTHDAY, DEPARTMENT_ID, CURATOR_ID, GROUP_ID, LOGIN, PASSWORD)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateSQL = "UPDATE " + table + " SET  ROLE_ID=?, FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE=?, ADDRESS=?," +
            "BIRTHDAY=?, DEPARTMENT_ID=?, CURATOR_ID=?, GROUP_ID=?, LOGIN=?, PASSWORD=? WHERE PERSON_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE PERSON_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (Person) jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return (Person) jdbcTemplate.queryForObject(findByNameSQL, new Object[]{name}, new NewRowMapper());
    }

    @Override
    public ParentBean findByLogin(String login) {
        return (Person) jdbcTemplate.queryForObject(findByLoginSQL, new Object[]{login}, new NewRowMapper());
    }

    @Override
    public List<ParentBean> findListByObject(Object obj) {
        String corText = "%" + obj + "%";
        return (List) jdbcTemplate.query(findNamesSQL, new Object[]{corText}, new NewRowMapper());
    }

    @Override
    public boolean insert(ParentBean item) {
        Person person = (Person) item;
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
        Person newPerson = (Person) item;
       jdbcTemplate.update(updateSQL, new Object[]{
               newPerson.getRoleId(),
               newPerson.getFirstName(),
               newPerson.getLastName(),
               newPerson.getEmail(),
               newPerson.getPhone(),
               newPerson.getAddress(),
               newPerson.getBirthday(),
               newPerson.getDepartmentId(),
               newPerson.getCuratorId(),
               newPerson.getGroupId(),
               newPerson.getLogin(),
               newPerson.getPassword(),
               newPerson.getId()
        });
        return true;
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
