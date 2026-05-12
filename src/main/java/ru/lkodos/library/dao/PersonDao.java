package ru.lkodos.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lkodos.library.model.Person;

import java.util.List;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL = "SELECT * FROM people";
    private static final String SAVE_SQL = "INSERT INTO people(name, year) VALUES(?, ?)";
    private static final String GET_BY_ID_SQL = "SELECT * FROM people WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE people SET name=?, year=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM people WHERE id=?";

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update(SAVE_SQL, person.getName(), person.getYear());
    }

    public Person getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_SQL,
                new BeanPropertyRowMapper<>(Person.class),
                id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(UPDATE_SQL, person.getName(), person.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}
