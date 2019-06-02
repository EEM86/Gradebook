package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.ParentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MessageDAOImpl implements DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String table = "L3G3_message";
    private String findAllSQL = "SELECT * FROM " + table;
    private String findByIdSQL = "SELECT * FROM " + table + " WHERE MESSAGE_ID=?";
    private String insertSQL = "INSERT INTO " + table +
            " (RECEIVER_ID, sender_id, message) VALUES (?, ?, ?)";
    private String updateSQL = "UPDATE " + table +
            " SET reseiver_id=?, sender_id=?, message=? WHERE MESSAGE_ID=?";
    private String deleteSQL = "DELETE FROM " + table + " WHERE MESSAGE_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    @Override
    public ParentBean findById(Integer id) {
        return (Message) jdbcTemplate.queryForObject(findByIdSQL,
                new Object[]{id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        Message message = (Message) item;
        jdbcTemplate.update(insertSQL, new Object[] {
                message.getReceiver_id(),
                message.getSender_id(),
                message.getMessage()
        });
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        Message message = (Message) item;
        jdbcTemplate.update(updateSQL, new Object[]{
                message.getReceiver_id(),
                message.getSender_id(),
                message.getMessage(),
                message.getId()
        });
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(deleteSQL, id) == 1;
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
