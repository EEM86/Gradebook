package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.BranchType;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContainerDAOImpl implements ContainerDAO<Container> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_CONTAINER";
    private static final String FIND_ALL =
            "SELECT c.ID, c.PARENT_ID, c.NAME, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
                    "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
                    "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID";

    private static final String FIND_BY_ID = "" +
            "SELECT c.ID, c.PARENT_ID, c.NAME, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.ID=?";
    private static final String FIND_BY_NAME =
            "SELECT c.ID, c.PARENT_ID, c.NAME, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
                    "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
                    "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
                    "WHERE c.NAME=?";
    private static final String FIND_GROUPS = "" +
            "SELECT c.ID, c.PARENT_ID, c.NAME, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.TYPE_ID=3";
    private static final String FIND_DEPARTMENTS = "" +
            "SELECT c.ID, c.PARENT_ID, c.NAME, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.TYPE_ID=2";
    private static final String INSERT_SQL =
            "INSERT INTO " + TABLE + " (PARENT_ID, NAME, CHIEF_ID, TYPE_ID, INSTITUTION_CITY, INSTITUTION_ADDRESS, PHONE)" +
            " VALUES (?, " +
                    "?, " +
                    "(SELECT ID FROM L3G3_PERSON WHERE UPPER(FIRST_NAME) = UPPER(?) AND UPPER(LAST_NAME) =  UPPER(?)), " +
                    "(SELECT TYPE_ID FROM L3G3_BRANCH_TYPE WHERE UPPER(TYPE_NAME) = UPPER(?)), " +
                    "?, " +
                    "?, " +
                    "?)";
    private static final String UPDATE_SQL =
            "UPDATE " + TABLE +
            " SET PARENT_ID=?, " +
            "NAME=?, " +
            "CHIEF_ID=(SELECT ID FROM L3G3_PERSON WHERE UPPER(FIRST_NAME) = UPPER(?) AND UPPER(LAST_NAME) =  UPPER(?)), " +
            "TYPE_ID=(SELECT TYPE_ID FROM L3G3_BRANCH_TYPE WHERE UPPER(TYPE_NAME) = UPPER(?)), " +
            "INSTITUTION_CITY=?, INSTITUTION_ADDRESS=?, PHONE=? " +
            "WHERE ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE ID=?";

    @Override
    public List<Container> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<Container>());
    }

    @Override
    public Container findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new NewRowMapper<Container>());
    }

    @Override
    public Container findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME, new Object[]{name}, new NewRowMapper<Container>());
    }

    @Override
    public boolean insert(Container item) {
        jdbcTemplate.update(INSERT_SQL,
                item.getParentId(),
                item.getName(),
                item.getChief().getFirstName(),
                item.getChief().getLastName(),
                item.getType().getTypeName(),
                item.getInstitutionCity(),
                item.getInstitutionAddress(),
                item.getPhone());
        return true;
    }

    @Override
    public boolean update(Container item) {
        jdbcTemplate.update(UPDATE_SQL,
                item.getParentId(),
                item.getName(),
                item.getChief().getFirstName(),
                item.getChief().getLastName(),
                item.getType().getTypeName(),
                item.getInstitutionCity(),
                item.getInstitutionAddress(),
                item.getPhone(),
                item.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    @Override
    public List<Container> findGroups() {
        return jdbcTemplate.query(FIND_GROUPS, new NewRowMapper<Container>());
    }

    @Override
    public List<Container> findDepatments() {
        return jdbcTemplate.query(FIND_DEPARTMENTS, new NewRowMapper<Container>());
    }

    private static final class NewRowMapper<P> implements RowMapper<Container> {

        @Override
        public Container mapRow(ResultSet resultSet, int i) throws SQLException {
            Person chief = (new BeanPropertyRowMapper<>(Person.class)).mapRow(resultSet, i);
            BranchType type = new BranchType();
            type.setId(resultSet.getInt(6));
            type.setTypeName(resultSet.getString(7));

            Container container = new Container();
            container.setId(resultSet.getInt(1));
            if ((resultSet.getObject(2) == null)) {
                container.setParentId((Integer) resultSet.getObject(2));
            } else {
                container.setParentId(Integer.valueOf(resultSet.getObject(2).toString()));
            }
            container.setName(resultSet.getString(3));
            container.setChief(chief);
            container.setType(type);
            container.setInstitutionCity(resultSet.getString(8));
            container.setInstitutionAddress(resultSet.getString(9));
            container.setPhone(resultSet.getString(10));
            return container;
        }
    }
}
