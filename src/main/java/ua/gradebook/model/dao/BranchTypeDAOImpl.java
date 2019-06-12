package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.BranchType;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value="BranchTypeDAO")
public class BranchTypeDAOImpl implements DAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_branch_type";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE TYPE_ID=?";
    private String findByNameSQL = "SELECT * FROM " + table + " WHERE TYPE_NAME=?";
    private String insertSQL = "INSERT INTO " + table + " (TYPE_NAME) VALUES (?)";
    private String updateSQL = "UPDATE " + table + " SET TYPE_NAME=? WHERE TYPE_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE TYPE_ID=?";

    public BranchTypeDAOImpl() {
    }

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, new NewRowMapper<BranchType>());
    }

    @Override
    public ParentBean findByName(String name) {
        return jdbcTemplate.queryForObject(findByNameSQL, new Object[]{name}, new NewRowMapper<BranchType>());
    }

    @Override
    public boolean insert(ParentBean item) {
        BranchType branchType = (BranchType) item;
        return (jdbcTemplate.update(insertSQL, branchType.getTypeName()) == 1);
    }

    @Override
    public boolean update(ParentBean item) {
        BranchType branchType = (BranchType) item;
        return jdbcTemplate.update(updateSQL, branchType.getTypeName(), branchType.getId()) == 1;
    }

    @Override
    public boolean delete(int id) {
        return (jdbcTemplate.update(deleteSQL, id) == 1);
    }

    private static final class NewRowMapper<P> implements RowMapper<BranchType> {

        @Override
        public BranchType mapRow(ResultSet resultSet, int i) throws SQLException {
            BranchType branchType = new BranchType();
            branchType.setId(resultSet.getInt(1));
            branchType.setTypeName(resultSet.getString(2));
            return branchType;
        }
    }
}
