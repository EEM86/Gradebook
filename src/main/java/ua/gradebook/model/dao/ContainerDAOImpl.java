package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Container;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ContainerDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_container";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE id=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL1 = "INSERT INTO " + table + " (id, name) VALUES (?, ?)";
    private String updateSQL = "UPDATE " + table + " SET id=?, name=? WHERE id=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE id=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
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

    @Override
    public Long getNextId() {
        return null;
    }

    private static final class NewRowMapper<P> implements RowMapper<Container> {

        @Override
        public Container mapRow(ResultSet resultSet, int i) throws SQLException {
            Container container = new Container();
            container.setId(resultSet.getInt(1));
            container.setParent_id(resultSet.getInt(2));
            container.setName(resultSet.getString(3));
            container.setChief_id(resultSet.getInt(4));
            container.setType(resultSet.getInt(5));
            container.setInstitution_city(resultSet.getString(6));
            container.setInstitution_address(resultSet.getString(7));
            container.setPhone(resultSet.getString(8));
            return container;
        }
    }
}
