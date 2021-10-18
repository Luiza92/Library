package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentsRepo implements StudentsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Students students;

    @Override
    public int insert(Students students) throws SQLException {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement("insert into students (name, surname, phone ) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, students.getName());
                statement.setString(2, students.getSurname());
                statement.setInt(3, students.getPhone());
                return statement;
            }
        }, holder);
        return ((Long) holder.getKey().longValue()).intValue();
    }

    @Override
    public Students get(int id) throws SQLException {
        String sql = ("select * from students where id = ?  ;");
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
        Students students = new Students();
        students.setName((String) result.get("name"));
        students.setSurname((String) result.get("surname"));
        students.setPhone((int) result.get("phone"));

        students.setId((int) result.get("Id"));

        String sql1 = ("SELECT \n" +
                "students.id AS student_id,\n" +
                "students.name AS student_name,\n" +
                "students.`phone` AS student_phone,\n" +
                "students.`surname` AS student_surname,\n" +
                "book.`id` AS book_id,\n" +
                "book.`title` AS book_title,\n" +
                "book.`quantity` AS book_quantity ,\n" +
                "book.`author` AS book_author\n" +
                "FROM students \n" +
                " LEFT JOIN students_books ON students_books.`students_id` = students.id\n" +
                " LEFT JOIN book ON students_books.`book_id`= book.`id`\n" +
                "WHERE students.id = ?  ;");
        List<Map<String, Object>> result1 = jdbcTemplate.queryForList(sql1, id);

        List<Book> bookList = new ArrayList<>();
        result1.forEach(students_books -> {

            Book book = new Book();

            book.setId((int) students_books.get("book_id"));
            book.setTitle((String) students_books.get("book_title"));
            book.setAuthor((String) students_books.get("book_author"));
            book.setQuantity((int) students_books.get("book_quantity"));


            bookList.add(book);

        });
        students.setBooks(bookList);


        return students;

    }

    @Override
    public boolean delete(String id) throws SQLException {

        String sql = "delete from students where id = ?;";
        int result = jdbcTemplate.update(sql, id);
        if (result == 0) {
            System.out.println("a new row has been delete.");
            return false;
        }
        return true;
    }


    @Override
    public int update(Students students) throws SQLException {

        String sql = "update students set name = ?, surname = ?, phone = ? where id = ?";
        System.err.println(students.getName() + ", " + students.getSurname() + ", " + students.getPhone() + ", " + students.getId());
        int result = jdbcTemplate.update(sql, students.getName(), students.getSurname(), students.getPhone(), students.getId());
        if (result > 0) {
            System.out.println("a new row has been update.");
            return students.getId();
        }
        return students.getId();
    }

    @Override
    public int insertStudents_books(int book_id, int students_id) throws SQLException {

        List<Map<String, Object>> result1 = jdbcTemplate.queryForList(
                "select students_id from students_books where book_id = ?;", book_id);
        List<Integer> students_ids = new ArrayList<>();
        result1.forEach(students_books -> {
            students_ids.add((Integer) students_books.get("students_id"));
        });
        return book_id;
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


}
