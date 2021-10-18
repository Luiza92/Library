//package com.example.library.entity;
//
//import com.example.library.model.Book;
//import com.example.library.model.Students;
//import lombok.Data;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.validator.constraints.Length;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import java.util.Set;
//
//@Data
//@Entity
//@DynamicInsert
//@DynamicUpdate
//@Table(name = "students")
//public class StudentsEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Long id;
//
//    @Column(name = "NAME", nullable = false)
//    private String Name;
//
//    @Column(name = "SURNAME", nullable = false)
//    private String Surname;
//
//    @Column(name = "PHONE", nullable = false)
//    private int Phone;
//
//    @ManyToMany
//    @JoinTable(name = "students_books",
//            joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "Id"))
//
//    private Set<BookEntity> books;
//
//
//
//



//    @OneToMany
//    @JoinColumn(name = "BOOK_ID", unique = true, nullable = false)
//    private BookEntity book;
//}



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "name")
//    @NotEmpty(message = "Please provide your name")
//    private String name;
//
//    @Column(name = "surname")
//    @NotEmpty(message = "Please provide your surname")
//    private String surname;
//
//    @Length(min = 9, message = "Ypur number must have at least 9")
//    @NotEmpty(message = "Please provide your number")
//    private int phone;
//
//
////   @ManyToMany(cascade = CascadeType.ALL)
////    @JoinTable(name = "students_books"),
////   JoinColumns = @JoinColumn(name = "book_id"),
////   inverseJoinColumns = @JoinColumn(name = "students_id"))
////    private Set<BookEntity> book;
//





