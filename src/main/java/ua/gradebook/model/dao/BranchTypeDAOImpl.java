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

    private static final String TABLE = "L3G3_branch_type";
    private static final String FIND_All = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE TYPE_ID=?";
    private static final String FIND_BY_NAME = "SELECT * FROM " + TABLE + " WHERE TYPE_NAME=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " (TYPE_NAME) VALUES (?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET TYPE_NAME=? WHERE TYPE_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE TYPE_ID=?";

    public BranchTypeDAOImpl() {
    }

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(FIND_All, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new NewRowMapper<BranchType>());
    }

    @Override
    public ParentBean findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME, new Object[]{name}, new NewRowMapper<BranchType>());
    }

    @Override
    public boolean insert(ParentBean item) {
        BranchType branchType = (BranchType) item;
        return (jdbcTemplate.update(INSERT_SQL, branchType.getTypeName()) == 1);
    }

    @Override
    public boolean update(ParentBean item) {
        BranchType branchType = (BranchType) item;
        return jdbcTemplate.update(UPDATE_SQL, branchType.getTypeName(), branchType.getId()) == 1;
    }

    @Override
    public boolean delete(int id) {
        return (jdbcTemplate.update(DELETE_SQL, id) == 1);
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
