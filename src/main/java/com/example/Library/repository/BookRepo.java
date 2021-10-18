package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepo<holder> implements BookRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Book book;

    @Override
    public int insert(Book book) throws SQLException {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement("insert into book (title, author, quantity) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setInt(3, book.getQuantity());
                return statement;
            }
        }, holder);
        return ((Long) holder.getKey().longValue()).intValue();


    }

    @Override
    public int insertStudents_books(int book_id, int students_id) throws SQLException {

        System.err.println(book_id);
        System.err.println(students_id);
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement("insert into students_books ( book_id , students_id) values (?,?)", Statement.RETURN_GENERATED_KEYS);

                statement.setInt(1, book_id);
                statement.setInt(2, students_id);

                return statement;
            }
        }, holder);
        return ((Long) holder.getKey().longValue()).intValue();
    }

    @Override
    public Book get(int id) throws SQLException {
//        String sql = ("SELECT book.*,students_books.`students_id` FROM book\n" +
//                "LEFT JOIN students_books ON students_books.`book_id`= book.`id`\n" +
//                "WHERE book.id = ? ;");
//

        String sql = ("SELECT book.* FROM book WHERE book.id = ? ;");
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
        Book book = new Book();
        book.setTitle((String) result.get("title"));
        book.setAuthor((String) result.get("author"));
        book.setQuantity((int) result.get("quantity"));
        book.setId((int) result.get("Id"));

        book.setStudentsId(this.getBookStudents(id));

        return book;

    }

    @Override
    public List<Integer> getBookStudents(int book_id) {
        List<Map<String, Object>> result1 = jdbcTemplate.queryForList(
                "select students_id from students_books where book_id = ?;", book_id);
        List<Integer> students_ids = new ArrayList<>();
        result1.forEach(students_books -> {
            students_ids.add((Integer) students_books.get("students_id"));
        });
        return students_ids;
    }

    @Override
    public boolean delete(String id) throws SQLException {

        String sql = "delete from book where id = ?;";
        int result = jdbcTemplate.update(sql, id);
        if (result == 0) {
            System.out.println("a new row has been delete.");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteStudents_books(int book_id, int students_id) throws SQLException {

        String sql = "delete from students_books where book_id = ? and students_id = ?;";
        int result = jdbcTemplate.update(sql, book_id, students_id);
        if (result == 0) {
            System.out.println("a new row has been delete.");
            return false;
        }
        return true;
    }

    @Override
    public int update(Book book) throws SQLException {

        String sql = "update book set title = ?, author = ?, quantity = ? where id = ?";
        System.err.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getQuantity() + ", " + book.getId());
        int result = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getQuantity(), book.getId());
        if (result > 0) {
            System.out.println("a new row has been update.");
            return book.getId();
        }
        return book.getId();

    }

    @Override
    public Book getById(String id) throws SQLException {

        return null;
    }


}

