package ua.gradebook.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.gradebook.model.beans.Message;
import ua.gradebook.model.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MessageDAOImpl implements DAOExtension<Message> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "L3G3_message";
    private static final String FIND_ALL =
            "SELECT l.MESSAGE_ID, p.ID, p.FIRST_NAME, p.LAST_NAME, p2.ID, p2.FIRST_NAME, p2.LAST_NAME, l.MESSAGE FROM " + TABLE + " l " +
             "LEFT JOIN L3G3_PERSON p ON l.RECEIVER_ID = p.ID " +
             "LEFT JOIN L3G3_PERSON p2 ON l.SENDER_ID = p2.ID";
    private static final String FIND_BY_ID =
            "SELECT l.MESSAGE_ID, p.ID, p.FIRST_NAME, p.LAST_NAME, p2.ID, p2.FIRST_NAME, p2.LAST_NAME, l.MESSAGE FROM " + TABLE + " l " +
                    "LEFT JOIN L3G3_PERSON p ON l.RECEIVER_ID = p.ID " +
                    "LEFT JOIN L3G3_PERSON p2 ON l.SENDER_ID = p2.ID " +
                    "WHERE l.RECEIVER_ID=? OR l.SENDER_ID=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE +
            " (RECEIVER_ID, SENDER_ID, MESSAGE) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE +
            " SET RECEIVER_ID=?, SENDER_ID=?, MESSAGE=? WHERE MESSAGE_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE MESSAGE_ID=?";

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper<Message>());
    }

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
                item.getReceiver().getId(),
                item.getSender().getId(),
                item.getMessageText());
        return true;
    }

    @Override
    public boolean update(Message item) {
        jdbcTemplate.update(UPDATE_SQL,
                item.getReceiver().getId(),
                item.getReceiver().getId(),
                item.getSender().getId(),
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

    private static final class NewRowMapper<P> implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            Person receiver = new Person();
            Person sender = new Person();
            Message message = new Message();
            message.setId(resultSet.getInt(1));
            receiver.setId(resultSet.getInt(2));
            receiver.setFirstName(resultSet.getString(3));
            receiver.setLastName(resultSet.getString(4));
            sender.setId(resultSet.getInt(5));
            sender.setFirstName(resultSet.getString(6));
            sender.setLastName(resultSet.getString(7));
            message.setMessageText(resultSet.getString(8));
            message.setReceiver(receiver);
            message.setSender(sender);
            return message;
        }
    }
}
