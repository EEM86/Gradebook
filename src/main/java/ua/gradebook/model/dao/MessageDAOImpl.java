package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MessageDAOImpl implements DAOExtension<Message> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_message";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE RECEIVER_ID=? OR SENDER_ID=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE +
            " (RECEIVER_ID, SENDER_ID, MESSAGE) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE +
            " SET RECEIVER_ID=?, SENDER_ID=?, MESSAGE=? WHERE MESSAGE_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE MESSAGE_ID=?";

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<Message>());
    }

    //ToDo make return type List?
    @Override
    public Message findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                new Object[]{id, id}, new NewRowMapper<Message>());
    }

    @Override
    public Message findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(Message item) {
        jdbcTemplate.update(INSERT_SQL,
                item.getReceiverId(),
                item.getSenderId(),
                item.getMessageText());
        return true;
    }

    @Override
    public boolean update(Message item) {
        jdbcTemplate.update(UPDATE_SQL,
                item.getReceiverId(),
                item.getSenderId(),
                item.getMessageText(),
                item.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    @Override
    public List<Message> findListByObject(Object id) {
        return jdbcTemplate.query(FIND_BY_ID, new Object[]{id, id}, new NewRowMapper<Message>());
    }

    @Override
    public Message findByLogin(String login) { return null; }

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
