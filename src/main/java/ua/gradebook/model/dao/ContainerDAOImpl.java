package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContainerDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_CONTAINER";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE ID=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE NAME=?";
    private String insertSQL = "INSERT INTO " + table + " (PARENT_ID, NAME, CHIEF_ID, TYPE_ID, INSTITUTION_CITY, INSTITUTION_ADDRESS, PHONE)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private String updateSQL = "UPDATE " + table + " SET PARENT_ID=?, NAME=?, CHIEF_ID=?, TYPE_ID=?, INSTITUTION_CITY=?, INSTITUTION_ADDRESS=?, PHONE=? " +
            "WHERE ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (Container) jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return (Container) jdbcTemplate.queryForObject(findByNameSQL, new Object[]{name}, new NewRowMapper());
    }

    @Override
    public boolean insert(ParentBean item) {
        Container container = (Container) item;
        jdbcTemplate.update(insertSQL, new Object[] {
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
    public boolean update(ParentBean item) {
        Container container = (Container) item;
        jdbcTemplate.update(updateSQL, new Object[]{
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
        return jdbcTemplate.update(deleteSQL, id) == 1;
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
