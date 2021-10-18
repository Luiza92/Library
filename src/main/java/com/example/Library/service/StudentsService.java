package com.example.library.service;


import com.example.library.model.Students;
import com.example.library.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class StudentsService {


    @Autowired
    StudentsRepository studentsRepository;




    public int add(Students students) throws SQLException {

        int studentsId = this.studentsRepository.insert(students);
        {
            System.err.println("add " + studentsId );
        }
        return studentsId;
    }

    public Students get(int id) throws SQLException {

        return this.studentsRepository.get(id);
    }


    public boolean delete(String id) throws SQLException {
        return this.studentsRepository.delete(id);

    }

    public int update(Students students) throws SQLException {
        int studentId= this.studentsRepository.update(students);
        {
            System.err.println("add " + studentId);
        }
        return studentId;
    }

    //  public User getByUsername(String username) throws SQLException {
    //      return this.userRepository.getByUsername(username);
    //  }





}
