package com.example.library.service;


import com.example.library.model.Book;
import com.example.library.model.Students;
import com.example.library.repository.BookRepo;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public int add(Book book, List<Integer> studentsId) throws SQLException {
        int bookId = this.bookRepository.insert(book);
        for (int studentId : studentsId) {
            this.bookRepository.insertStudents_books(bookId, studentId);
        }


        System.err.println("add " + bookId);
        return bookId;
    }

    public Book get(int id) throws SQLException {

        return this.bookRepository.get(id);
    }

    public Book getById(String id) throws SQLException {
        return this.bookRepository.getById(id);
    }

    public boolean delete(String id) throws SQLException {
        return this.bookRepository.delete(id);

    }

    public int update(Book book, List<Integer> studentsId) throws SQLException {
        int bookId = this.bookRepository.update(book);

        System.err.println(" update " + bookId);
        this.bookRepository.getBookStudents(bookId).forEach(students_id -> {
            try {
                this.bookRepository.deleteStudents_books(bookId, students_id);
            } catch (SQLException ignored) {
            }
        });

        for (int studentId : studentsId) {
            this.bookRepository.insertStudents_books(bookId, studentId);
        }

        return bookId;
    }


}
