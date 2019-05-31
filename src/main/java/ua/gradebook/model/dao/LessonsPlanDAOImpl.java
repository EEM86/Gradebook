package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.LessonsPlan;
import ua.gradebook.model.beans.ParentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LessonsPlanDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_lessonsplan";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE id=?";
  //  private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL1 = "INSERT INTO " + table + " (id, disc_id, teacher_id, group_id, hours) VALUES (?, ?, ?, ?, ?)";
    private String updateSQL = "UPDATE " + table + " SET id=?, disc_id=?, teacher_id=?, group_id=?, hours=? WHERE id=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE id=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Long id) {
        return jdbcTemplate.queryForObject(findByIdSQL, LessonsPlan.class, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        LessonsPlan lessonsPlan = (LessonsPlan) item;
        String insertSQL = "INSERT INTO " + table + " (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(insertSQL, new String[] {"id"});
                        ps.setInt(1, lessonsPlan.getDisc_id());
                        return ps;
                    }
                },
                keyHolder);
        return jdbcTemplate.update(insertSQL1, keyHolder.getKey(), lessonsPlan.getDisc_id()) != 0;
    }

    @Override
    public boolean update(ParentBean item) {
        LessonsPlan lessonsPlan = (LessonsPlan) item;
        return jdbcTemplate.update(updateSQL, lessonsPlan.getId(), lessonsPlan.getDisc_id(),
                lessonsPlan.getTeacher_id(), lessonsPlan.getGroup_id(),
                lessonsPlan.getHours(), lessonsPlan.getId()) != 0;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(deleteSQL, id) != 0;
    }

    @Override
    public Long getNextId() {
        String sql = "SELECT mylab3_role.id.nextval as id from dual";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
/*                ps.setLong(1, i);
                ps.setLong(2, parameterID);*/
            }
        }, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }
                return null;
            }
        });
    }

    private static final class NewRowMapper<P> implements RowMapper<LessonsPlan> {

        @Override
        public LessonsPlan mapRow(ResultSet resultSet, int i) throws SQLException {
            LessonsPlan lessonsPlan = new LessonsPlan();
            lessonsPlan.setId(resultSet.getInt(1));
            lessonsPlan.setDisc_id(resultSet.getInt(2));
            lessonsPlan.setTeacher_id(resultSet.getInt(3));
            lessonsPlan.setGroup_id(resultSet.getInt(4));
            lessonsPlan.setHours(resultSet.getInt(5));
            return lessonsPlan;
        }
    }
}
