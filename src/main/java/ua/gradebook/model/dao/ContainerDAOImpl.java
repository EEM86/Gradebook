package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Container;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value="ContainerDAO")
public class ContainerDAOImpl implements DAO<Container> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_CONTAINER";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE ID=?";
    private static final String FIND_BY_NAME = "SELECT * FROM " + TABLE + " WHERE NAME=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " (PARENT_ID, NAME, CHIEF_ID, TYPE_ID, INSTITUTION_CITY, INSTITUTION_ADDRESS, PHONE)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET PARENT_ID=?, NAME=?, CHIEF_ID=?, TYPE_ID=?, INSTITUTION_CITY=?, INSTITUTION_ADDRESS=?, PHONE=? " +
            "WHERE ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE ID=?";

    @Override
    public List<Container> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper());
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
        Container container = (Container) item;
        jdbcTemplate.update(INSERT_SQL, new Object[] {
                container.getParentId(),
                container.getName(),
                container.getChiefId(),
                container.getTypeId(),
                container.getInstitutionCity(),
                container.getInstitutionAddress(),
                container.getPhone()
        });
        return true;
    }

    @Override
    public boolean update(Container item) {
        Container container = (Container) item;
        jdbcTemplate.update(UPDATE_SQL, new Object[]{
                container.getParentId(),
                container.getName(),
                container.getChiefId(),
                container.getTypeId(),
                container.getInstitutionCity(),
                container.getInstitutionAddress(),
                container.getPhone(),
                container.getId()
        });
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    private static final class NewRowMapper<P> implements RowMapper<Container> {

        @Override
        public Container mapRow(ResultSet resultSet, int i) throws SQLException {
            Container container = new Container();
            container.setId(resultSet.getInt(1));
            if ((resultSet.getObject(2) == null)) {
                container.setParentId((Integer) resultSet.getObject(2));
            } else {
                container.setParentId(Integer.valueOf(resultSet.getObject(2).toString()));
            }
            container.setName(resultSet.getString(3));
            container.setChiefId(resultSet.getInt(4));
            container.setTypeId(resultSet.getInt(5));
            container.setInstitutionCity(resultSet.getString(6));
            container.setInstitutionAddress(resultSet.getString(7));
            container.setPhone(resultSet.getString(8));
            return container;
        }
    }
}
