package com.example.library.repository;

import com.example.library.model.Students;


import java.sql.SQLException;


public interface StudentsRepository {


    int insert(Students students) throws SQLException;

    Students get(int id) throws SQLException;

    boolean delete(String id) throws SQLException;

    int update(Students students) throws SQLException;

    int insertStudents_books(int book_id , int students_id) throws SQLException ;
    boolean deleteStudents_books(int book_id, int students_id) throws SQLException ;

   // Students getById(String username) throws SQLException;
}
