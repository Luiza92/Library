//package com.example.library.entity;
//
//import com.example.library.model.Students;
//import com.sun.javafx.beans.IDProperty;
//import lombok.Data;
//import lombok.ToString;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Set;
//
//@Data
//@ToString(exclude = "students")
//@Entity
//@DynamicInsert
//@DynamicUpdate
//@Table(name = "book")
//public class BookEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Long id;
//
//    @Column(name = "TITLE", nullable = false)
//    private String title;
//
//    @Column(name = "AUTHOR", nullable = false)
//    private String author;
//
//    @Column(name = "QUANTITY", nullable = false)
//    private String quantity;
//
//    @ManyToMany
//    @JoinTable(name = "students_books",
//            joinColumns = @JoinColumn(name = "books_id", referencedColumnName = "id"))
//    private Set<StudentsEntity> authors;
//
//}
