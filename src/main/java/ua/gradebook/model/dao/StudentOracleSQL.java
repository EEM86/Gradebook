package ua.gradebook.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentOracleSQL implements DAO {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void setDataSource() {

    }
}
