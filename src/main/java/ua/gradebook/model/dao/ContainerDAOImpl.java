package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
                    "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
                    "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
                    "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID";
    private static final String FIND_BY_ID = "" +
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.ID=?";
    private static final String FIND_BY_NAME =
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
                    "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
                    "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
                    "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
                    "WHERE c.NAME=?";

    private static final String FIND_ALL_GROUPS = "" +
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.TYPE_ID=" + BranchType.GROUP_ID;

    private static final String FIND_GROUPS = "" +
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.TYPE_ID=" + BranchType.GROUP_ID + " AND connect_by_root(c.id) = ? " +
            "CONNECT BY PRIOR c.id = c.PARENT_ID " +
            "START WITH c.PARENT_ID is NULL";

    private static final String FIND_ALL_DEPARTMENTS = "" +
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.TYPE_ID=" + BranchType.DEPARTMENT_ID;

    private static final String FIND_DEPARTMENTS = "" +
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.TYPE_ID=" + BranchType.DEPARTMENT_ID + " AND connect_by_root(c.id) = ? " +
            "CONNECT BY PRIOR c.id = c.PARENT_ID " +
            "START WITH c.PARENT_ID is NULL";

    private static final String FIND_ROOT_CONTAINER = "" +
            "SELECT c.ID, c.PARENT_ID, d.NAME, c.NAME, c.CHIEF_ID, p.FIRST_NAME, p.LAST_NAME, c.TYPE_ID, b.TYPE_NAME, c.INSTITUTION_CITY, c.INSTITUTION_ADDRESS, c.PHONE FROM " + TABLE + " c " +
            "LEFT JOIN L3G3_CONTAINER d ON c.PARENT_ID = d.ID " +
            "LEFT JOIN L3G3_PERSON p ON c.CHIEF_ID = p.ID " +
            "LEFT JOIN L3G3_BRANCH_TYPE b ON c.TYPE_ID = b.TYPE_ID " +
            "WHERE c.ID=(SELECT connect_by_root(id) from L3G3_CONTAINER where id = ? connect by prior id = PARENT_ID start with PARENT_ID is null)";

    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " (PARENT_ID, NAME, CHIEF_ID, TYPE_ID, INSTITUTION_CITY, INSTITUTION_ADDRESS, PHONE)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET PARENT_ID=?, NAME=?, CHIEF_ID=?, TYPE_ID=?, INSTITUTION_CITY=?, INSTITUTION_ADDRESS=?, PHONE=? " +
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
    public Container findRootContainer(int groupId) {
        return jdbcTemplate.queryForObject(FIND_ROOT_CONTAINER, new Object[]{groupId}, new NewRowMapper<Container>());
    }

    @Override
    public boolean insert(Container item) {
        jdbcTemplate.update(INSERT_SQL,
                item.getParent().getId(),
                item.getName(),
                item.getChief().getId(),
                item.getType().getId(),
                item.getInstitutionCity(),
                item.getInstitutionAddress(),
                item.getPhone());
        return true;
    }

    @Override
    public boolean update(Container item) {
        jdbcTemplate.update(UPDATE_SQL,
                item.getParent().getId(),
                item.getName(),
                item.getChief().getId(),
                item.getType().getId(),
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

    /**
     * This request returns all groups from the table.
     */
    @Override
    public List<Container> findGroups() {
        return jdbcTemplate.query(FIND_ALL_GROUPS, new NewRowMapper<Container>());
    }

    /**
     * This request returns child groups of a parent container
     */
    @Override
    public List<Container> findGroups(int rootId) {
        return jdbcTemplate.query(FIND_GROUPS, new Object[]{rootId}, new NewRowMapper<Container>());
    }

    /**
     * This request returns all departments from the table.
     */
    @Override
    public List<Container> findDepartments() {
        return jdbcTemplate.query(FIND_ALL_DEPARTMENTS, new NewRowMapper<Container>());
    }

    /**
     * This request returns child departments of a parent container
     */
    @Override
    public List<Container> findDepartments(int rootId) {
        return jdbcTemplate.query(FIND_DEPARTMENTS, new Object[]{rootId}, new NewRowMapper<Container>());
    }

    private static final class NewRowMapper<P> implements RowMapper<Container> {

        @Override
        public Container mapRow(ResultSet resultSet, int i) throws SQLException {
            Container container = new Container();
            container.setId(resultSet.getInt(1));

            if (resultSet.getInt(2) != 0) {
                Container parent = new Container();
                parent.setId(resultSet.getInt(2));
                parent.setName(resultSet.getString(3));
                container.setParent(parent);
            }

            container.setName(resultSet.getString(4));

            Person chief = new Person();
            chief.setId(resultSet.getInt(5));
            chief.setFirstName(resultSet.getString(6));
            chief.setLastName(resultSet.getString(7));
            container.setChief(chief);

            BranchType type = new BranchType();
            type.setId(resultSet.getInt(8));
            type.setTypeName(resultSet.getString(9));
            container.setType(type);

            container.setInstitutionCity(resultSet.getString(10));
            container.setInstitutionAddress(resultSet.getString(11));
            container.setPhone(resultSet.getString(12));
            return container;
        }
    }
}
