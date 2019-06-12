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

    private static final String TABLE = "L3G3_message";
    private static final String FIND_ALL = "SELECT * FROM " + TABLE;
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE RECEIVER_ID=? OR SENDER_ID=?";
    private static final String INSERT_SQL = "INSERT INTO " + TABLE +
            " (RECEIVER_ID, SENDER_ID, MESSAGE) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE +
            " SET RECEIVER_ID=?, SENDER_ID=?, MESSAGE=? WHERE MESSAGE_ID=?";
    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE MESSAGE_ID=?";

    @Override
    public List<ParentBean> findAll() {
        return jdbcTemplate.query(FIND_ALL, new NewRowMapper());
    }

    //ToDo make return type List?
    @Override
    public ParentBean findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                new Object[]{id, id}, new NewRowMapper<Message>());
    }

    @Override
    public ParentBean findByName(String name) {
        return null;
    }

    @Override
    public boolean insert(ParentBean item) {
        Message message = (Message) item;
        jdbcTemplate.update(INSERT_SQL, new Object[] {
                message.getReceiverId(),
                message.getSenderId(),
                message.getMessageText()
        });
        return true;
    }

    @Override
    public boolean update(ParentBean item) {
        Message message = (Message) item;
        jdbcTemplate.update(UPDATE_SQL, new Object[]{
                message.getReceiverId(),
                message.getSenderId(),
                message.getMessageText(),
                message.getId()
        });
        return true;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(DELETE_SQL, id) == 1;
    }

    @Override
    public List<ParentBean> findListByObject(Object id) {
        return (List) jdbcTemplate.query(FIND_BY_ID, new Object[]{id, id}, new NewRowMapper());
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
