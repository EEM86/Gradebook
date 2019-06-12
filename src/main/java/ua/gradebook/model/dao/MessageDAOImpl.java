package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.ParentBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value="MessageDAO")
public class MessageDAOImpl implements DAOExtension {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String table = "L3G3_message";
    private static final String findAllSQL = "SELECT * FROM " + table;
    private static final String findByIdSQL = "SELECT * FROM " + table + " WHERE RECEIVER_ID=? OR SENDER_ID=?";
    private static final String insertSQL = "INSERT INTO " + table +
            " (RECEIVER_ID, SENDER_ID, MESSAGE) VALUES (?, ?, ?)";
    private static final String updateSQL = "UPDATE " + table +
            " SET RECEIVER_ID=?, SENDER_ID=?, MESSAGE=? WHERE MESSAGE_ID=?";
    private static final String deleteSQL = "DELETE FROM " + table + " WHERE MESSAGE_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(findAllSQL, new NewRowMapper());
    }

    //ToDo make return type List?
    @Override
    public ParentBean findById(Integer id) {
        return jdbcTemplate.queryForObject(findByIdSQL,
                new Object[]{id, id}, new NewRowMapper<Message>());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        Message message = (Message) item;
        jdbcTemplate.update(insertSQL, new Object[] {
                message.getReceiverId(),
                message.getSenderId(),
                message.getMessageText()
        });
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        Message message = (Message) item;
        jdbcTemplate.update(updateSQL, new Object[]{
                message.getReceiverId(),
                message.getSenderId(),
                message.getMessageText(),
                message.getId()
        });
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(deleteSQL, id) == 1;
    }

    @Override
    public List<ParentBean> findListByObject(Object id) {
        return (List) jdbcTemplate.query(findByIdSQL, new Object[]{id, id}, new NewRowMapper());
    }

    @Override
    public ParentBean findByLogin(String login) { return null; }

    private static final class NewRowMapper<P> implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            Message message = new Message();
            message.setId(resultSet.getInt(1));
            message.setReceiverId(resultSet.getInt(2));
            message.setSenderId(resultSet.getInt(3));
            message.setMessageText(resultSet.getString(4));
            return message;
        }
    }
}
