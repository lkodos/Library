package ru.lkodos.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lkodos.library.model.Book;
import ru.lkodos.library.model.Person;

import java.util.List;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL = "SELECT * FROM books";
    private static final String GET_ALL_BY_PERSON_ID_SQL = "SELECT * FROM books WHERE person_id = ?";
    private static final String SAVE_SQL = "INSERT INTO books(title, author, year) VALUES(?, ?, ?)";
    private static final String GET_BY_ID_SQL = "SELECT * FROM books WHERE id = ?";
    private static final String RELEASE_BOOK_BY_ID_SQL = "UPDATE books SET person_id = NULL WHERE id = ?";
    private static final String ISSUE_BOOK_BY_ID_SQL = "UPDATE books SET person_id = ? WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE books SET title=?, author=?, year=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM books WHERE id=?";

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> getAllByPersonId(int personId) {
        return jdbcTemplate.query(GET_ALL_BY_PERSON_ID_SQL, new BeanPropertyRowMapper<>(Book.class), personId);
    }

    public void save(Book book) {
        jdbcTemplate.update(SAVE_SQL, book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_SQL,
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }

    public void releaseBook(int id) {
        jdbcTemplate.update(RELEASE_BOOK_BY_ID_SQL, id);
    }

    public void issueBook(int id, Person person) {
        jdbcTemplate.update(ISSUE_BOOK_BY_ID_SQL, person.getId(), id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update(UPDATE_SQL, book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}
