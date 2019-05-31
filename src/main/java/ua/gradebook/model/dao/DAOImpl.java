package ua.gradebook.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.gradebook.model.beans.Person;
import ua.gradebook.model.beans.Roles;

import javax.sql.DataSource;

public class DAOImpl implements DAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DAOImpl() {
    }

    public List<Person> getPerson() {

        return jdbcTemplate.query("SELECT * FROM L3G3_PERSON", new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person = new Person();
                person.setPerson_id(resultSet.getInt(1));
                person.setFirstName(resultSet.getString(3));
                person.setLastName(resultSet.getString(4));
                System.out.println(person.toString());
                return person;
            }
        });
    }
    public List<Roles> getRoles() {

        return jdbcTemplate.query("SELECT * FROM L3G3_ROLE", new RowMapper<Roles>() {
            @Override
            public Roles mapRow(ResultSet resultSet, int i) throws SQLException {
                Roles roles = new Roles(resultSet.getInt(1), resultSet.getString(2));
                System.out.println(roles.toString());
                return roles;
            }
        });
    }
}
