package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.ParentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MessageDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_message";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE id=?";
  //  private String findByNameSQL = "SELECT * FROM " + table + " WHERE name=?";
    private String insertSQL1 = "INSERT INTO " + table + " (id, reseiver_id, sender_id, message) VALUES (?, ?, ?, ?)";
    private String updateSQL = "UPDATE " + table + " SET id=?, reseiver_id=?, sender_id=?, message=? WHERE id=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE id=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return jdbcTemplate.queryForObject(findByIdSQL, Message.class, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        Message message = (Message) item;
        String insertSQL = "INSERT INTO " + table + " (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(insertSQL, new String[] {"id"});
                        ps.setString(1, message.getMessage());
                        return ps;
                    }
                },
                keyHolder);
        return jdbcTemplate.update(insertSQL1, keyHolder.getKey(), message.getMessage()) != 0;
    }

    @Override
    public boolean update(ParentBean item) {
        Message message = (Message) item;
        return jdbcTemplate.update(updateSQL, message.getId(), message.getReceiver_id(),
                message.getSender_id(), message.getMessage(), message.getId()) != 0;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(deleteSQL, id) != 0;
    }

    @Override
    public Long getNextId() {
        String sql = "SELECT mylab3_message.id.nextval as id from dual";
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

    private static final class NewRowMapper<P> implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            Message message = new Message();
            message.setId(resultSet.getInt(1));
            message.setReceiver_id(resultSet.getInt(2));
            message.setSender_id(resultSet.getInt(3));
            message.setMessage(resultSet.getString(4));
            return message;
        }
    }
}
