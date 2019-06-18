package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.beans.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO<Person> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_PERSON";
    private static final String FIND_STUDENTS = "SELECT * FROM " + TABLE + " WHERE ROLE_ID=2";
    private static final String FIND_TEACHERS = "SELECT * FROM " + TABLE + " WHERE ROLE_ID=3";
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE PERSON_ID=?";
    private static final String findByNameSQL = "SELECT * FROM " + TABLE + " WHERE LAST_NAME=?";
    String findByLoginSQL = "SELECT * FROM " + TABLE + " WHERE LOGIN=?";
    private static final String FIND_NAMES = "SELECT * FROM " + TABLE + " WHERE UPPER(LAST_NAME) LIKE UPPER(?)";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE;
    private static final String FIND_ALL =
            "SELECT p.ID, r.ROLE_NAME, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, " +
                    "c.NAME, p2.FIRST_NAME, p2.LAST_NAME, c2.NAME, p.login, p.password FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID";

//    private static final String FIND_BY_ID =
//            "SELECT p.ID, r.ROLE_NAME, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, " +
//                    "c.NAME, p2.FIRST_NAME, p2.LAST_NAME, c2.NAME, p.login, p.password FROM " + TABLE + " p " +
//                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
//                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
//                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
//                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
//                    "WHERE p.ID=?";
//    private static final String findByNameSQL =
//            "SELECT p.ID, r.ROLE_NAME, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, " +
//                    "c.NAME, p2.FIRST_NAME, p2.LAST_NAME, c2.NAME, p.login, p.password FROM " + TABLE + " p " +
//                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
//                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
//                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
//                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
//                    "WHERE p.LAST_NAME=?";
//    String findByLoginSQL =
//            "SELECT p.ID, r.ROLE_NAME, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, " +
//                    "c.NAME, p2.FIRST_NAME, p2.LAST_NAME, c2.NAME, p.login, p.password FROM " + TABLE + " p " +
//                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
//                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
//                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
//                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
//                    "WHERE p.LOGIN=?";
//    private static final String FIND_NAMES =
//            "SELECT p.ID, r.ROLE_NAME, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, " +
//                    "c.NAME, p2.FIRST_NAME, p2.LAST_NAME, c2.NAME, p.login, p.password FROM " + TABLE + " p " +
//                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
//                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
//                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
//                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
//                    "WHERE UPPER(p.LAST_NAME) LIKE UPPER(?)";
//    private static final String INSERT_SQL =
//            "INSERT INTO " + TABLE
//            + " (ROLE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, ADDRESS, BIRTHDAY, DEPARTMENT_ID, CURATOR_ID, GROUP_ID, LOGIN, PASSWORD)"
//            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                item.getRole().getId(),
                item.getFirstName(),
                item.getLastName(),
                item.getEmail(),
                item.getPhone(),
                item.getAddress(),
                item.getBirthday(),
                item.getDepartment().getId(),
                item.getCurator().getId(),
                item.getGroup().getId(),
                item.getLogin(),
                item.getPassword());
        return true;
    }

    @Override
    public boolean update(Person item) {
       jdbcTemplate.update(UPDATE_SQL,
               item.getRole().getId(),
               item.getFirstName(),
               item.getLastName(),
               item.getEmail(),
               item.getPhone(),
               item.getAddress(),
               item.getBirthday(),
               item.getDepartment().getId(),
               item.getCurator().getId(),
               item.getGroup().getId(),
               item.getLogin(),
               item.getPassword());
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    private static final class NewRowMapper<P> implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            Container department = new Container();
            Person curator = new Person();
            Container group = new Container();
            Person person = new Person();
            person.setId(resultSet.getInt(1));
            role.setRoleName(resultSet.getString(2));
            person.setFirstName(resultSet.getString(3));
            person.setLastName(resultSet.getString(4));
            person.setEmail(resultSet.getString(5));
            person.setPhone(resultSet.getString(6));
            person.setAddress(resultSet.getString(7));
            person.setBirthday(resultSet.getDate(8));
            department.setName(resultSet.getString(9));
            curator.setFirstName(resultSet.getString(10));
            curator.setLastName(resultSet.getString(11));
            group.setName(resultSet.getString(12));
            person.setLogin(resultSet.getString(13));
            person.setPassword(resultSet.getString(14));
            person.setRole(role);
            person.setDepartment(department);
            person.setCurator(curator);
            person.setGroup(group);
            return person;
        }
    }
}
