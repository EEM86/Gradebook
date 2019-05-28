package ua.gradebook.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public interface DAO {
    //void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    void setDataSource(DataSource dataSource);
}
