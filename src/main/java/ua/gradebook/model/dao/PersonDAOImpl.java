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
    private static final String FIND_STUDENTS =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.ROLE_ID=" + Role.STUDENT_ID;

    private static final String FIND_TEACHERS =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.ROLE_ID=" + Role.TEACHER_ID;

    private static final String FIND_ALL =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID";

    private static final String FIND_BY_ID =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.ID=?";

    private static final String findByNameSQL =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.LAST_NAME=?";

    private static final String findByLoginSQL =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.LOGIN=?";
    private static final String findByEmailSQL =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.EMAIL=?";
    private static final String findByPhoneSQL =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.PHONE=?";

    private static final String findAllWithoutOneLoginSQL =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.ID!=?";

    private static final String FIND_NAMES =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE UPPER(p.LAST_NAME) LIKE UPPER(?)";

    private static final String FIND_PERSONS_FROM_GROUP =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.GROUP_ID = ?";

    private static final String FIND_PERSONS_FROM_DEPARTMENT =
            "SELECT r.ROLE_ID, r.ROLE_NAME, p.ID, p.FIRST_NAME, p.LAST_NAME, p.EMAIL, p.PHONE, p.ADDRESS, p.BIRTHDAY, p.login, p.password, " +
                    "c.*, p2.*, c2.*  FROM " + TABLE + " p " +
                    "LEFT JOIN L3G3_ROLE r ON p.ROLE_ID = r.ROLE_ID " +
                    "LEFT JOIN L3G3_CONTAINER c on p.DEPARTMENT_ID = c.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON p.CURATOR_ID = p2.ID " +
                    "LEFT JOIN L3G3_CONTAINER c2 ON p.GROUP_ID = c2.ID " +
                    "WHERE p.DEPARTMENT_ID = ?";

    private static final String INSERT_SQL =
            "INSERT INTO " + TABLE
            + " (ROLE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, ADDRESS, BIRTHDAY, DEPARTMENT_ID, CURATOR_ID, GROUP_ID, LOGIN, PASSWORD)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET  ROLE_ID=?, FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE=?, ADDRESS=?," +
            "BIRTHDAY=?, DEPARTMENT_ID=?, CURATOR_ID=?, GROUP_ID=?, LOGIN=?, PASSWORD=? WHERE ID=?";

    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE ID=?";

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findStudents() {
        return jdbcTemplate.query(FIND_STUDENTS, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findTeacher() {
        return jdbcTemplate.query(FIND_TEACHERS, new NewRowMapper<Person>());
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
    public Person findByEmail(String email) {
        return jdbcTemplate.queryForObject(findByEmailSQL, new Object[]{email}, new NewRowMapper<Person>());
    }

    @Override
    public Person findByPhone(String phone) {
        return jdbcTemplate.queryForObject(findByPhoneSQL, new Object[]{phone}, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findAllWithoutOneId(Integer id) {
        return jdbcTemplate.query(findAllWithoutOneLoginSQL, new Object[]{id}, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findListByObject(Object obj) {
        String corText = "%" + obj + "%";
        return jdbcTemplate.query(FIND_NAMES, new Object[]{corText}, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findPersonsFromGroup(Integer groupId) {
        return jdbcTemplate.query(FIND_PERSONS_FROM_GROUP, new Object[]{groupId}, new NewRowMapper<Person>());
    }

    @Override
    public List<Person> findPersonsFromDepartment(Integer departmentId) {
        return jdbcTemplate.query(FIND_PERSONS_FROM_DEPARTMENT, new Object[]{departmentId}, new NewRowMapper<Person>());
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
            Role role = new Role();
            Container department = new Container();
            Person curator = new Person();
            Container group = new Container();

            role.setId(resultSet.getInt(1));
            role.setRoleName(resultSet.getString(2));

            person.setId(resultSet.getInt(3));
            person.setFirstName(resultSet.getString(4));
            person.setLastName(resultSet.getString(5));
            person.setEmail(resultSet.getString(6));
            person.setPhone(resultSet.getString(7));
            person.setAddress(resultSet.getString(8));
            person.setBirthday(resultSet.getDate(9));
            person.setLogin(resultSet.getString(10));
            person.setPassword(resultSet.getString(11));

            if (resultSet.getInt(12) != 0) {
                department.setId(resultSet.getInt(12));
//                department.setParentId(resultSet.getInt(13));
                department.setName(resultSet.getString(14));
//                Person deptPerson = new Person();
//                deptPerson.setId(resultSet.getInt(15));
//                BranchType branchType = new BranchType();
//                branchType.setId(resultSet.getInt(16));
//                department.setInstitutionCity(resultSet.getString(17));
//                department.setInstitutionAddress(resultSet.getString(18));
//                department.setPhone(resultSet.getString(19));
//                department.setChief(deptPerson);
//                department.setType(branchType);
            }

            curator.setId(resultSet.getInt(20));
//            Role curatorRole = new Role();
//            curatorRole.setId(resultSet.getInt(21));
            curator.setFirstName(resultSet.getString(22));
            curator.setLastName(resultSet.getString(23));
//            curator.setEmail(resultSet.getString(24));
//            curator.setPhone(resultSet.getString(25));
//            curator.setAddress(resultSet.getString(26));
//            curator.setBirthday(resultSet.getDate(27));
//            Container curatorDept = new Container();
//            curatorDept.setId(resultSet.getInt(28));
//            Person curatorPerson = new Person();
//            curatorPerson.setId(resultSet.getInt(29));
//            Container curatorGroup = new Container();
//            curatorGroup.setId(resultSet.getInt(30));
//            curator.setLogin(resultSet.getString(31));
//            curator.setPassword(resultSet.getString(32));
//            curator.setRole(curatorRole);
//            curator.setDepartment(curatorDept);
//            curator.setGroup(curatorGroup);
//            curator.setCurator(curatorPerson);

            if (resultSet.getInt(33) != 0) {
                group.setId(resultSet.getInt(33));
//                group.setParentId(resultSet.getInt(34));
                group.setName(resultSet.getString(35));
//                Person chiefGroup = new Person();
//                chiefGroup.setId(resultSet.getInt(36));
//                BranchType typeGroup = new BranchType();
//                typeGroup.setId(resultSet.getInt(37));
//                group.setInstitutionCity(resultSet.getString(38));
//                group.setInstitutionAddress(resultSet.getString(39));
//                group.setPhone(resultSet.getString(40));
//                group.setChief(chiefGroup);
//                group.setType(typeGroup);
            }

            person.setRole(role);
            person.setDepartment(department);
            person.setCurator(curator);
            person.setGroup(group);
            return person;
        }
    }
}
