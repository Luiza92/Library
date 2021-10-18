package com.example.library.repository;

import com.example.library.model.Book;



import java.sql.SQLException;
import java.util.List;

public interface BookRepository {
    List<Integer> getBookStudents(int book_id);

    int insert(Book book) throws SQLException;

    Book get(int id) throws SQLException;

    boolean delete(String id) throws SQLException;

    int update(Book book) throws SQLException;

    Book getById(String id) throws SQLException;

    int insertStudents_books(int book_id , int students_id) throws SQLException ;
    boolean deleteStudents_books(int book_id, int students_id) throws SQLException ;
}
