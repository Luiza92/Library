package com.example.library.controller;


import com.example.library.model.Book;
import com.example.library.model.Students;
import com.example.library.service.StudentsService;
import com.example.library.utill.Validation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    private Validation validation = new Validation();


    @PostMapping(path = "/api/students", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStudents(@ModelAttribute Students modelTO) throws JSONException {

        try {
            if (validation.isValidName(modelTO.getName()) == false) {
                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid name ");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if (validation.isValidSurname(modelTO.getSurname()) == false) {
                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid surname");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            if (validation.isValidPhone(modelTO.getPhone()) == false) {
                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid phone");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

            }

            System.err.println("Name - " + modelTO.getName());
            System.err.println("Surname - " + modelTO.getSurname());
            System.err.println("Phone  - " + modelTO.getPhone());


            Students students = new Students();

            students.setName(modelTO.getName());
            students.setSurname(modelTO.getSurname());
            students.setPhone(modelTO.getPhone());

            int studentsId = this.studentsService.add(students);
            Students students1 = this.studentsService.get(studentsId);


            JSONObject res = new JSONObject();
            res.put("id", students1.getId());
            res.put("name", students1.getName());
            res.put("surname", students1.getSurname());
            res.put("phone", students1.getPhone());


            return new ResponseEntity<>(res.toString(), HttpStatus.OK);

        } catch (DuplicateKeyException ex) {
            JSONObject res = new JSONObject();
            res.put("error_message", "DUPLICATE ERROR MESSAGE " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception ex) {
            JSONObject res = new JSONObject();
            res.put("error_message", "not found " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/api/students/{students_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudents(@PathVariable("students_id") String students_id) throws JSONException {
        try {

            System.err.print("get " + students_id);

            JSONObject res = new JSONObject();
            Students students = this.studentsService.get(Integer.parseInt(students_id));

            res.put("students_name", students.getName());
            res.put("students_surname", students.getSurname());
            res.put("students_phone", students.getPhone());
            res.put("students_id", students.getId());

            JSONArray bookList = new JSONArray();
            for (Book book : students.getBooks()
            ) {
                JSONObject bookobject = new JSONObject();

                bookobject.put("book_id", book.getId());
                bookobject.put("book_title", book.getTitle());
                bookobject.put("book_author", book.getAuthor());
                bookobject.put("book_quantity", book.getQuantity());

                bookList.put(bookobject);


            }
            res.put("students_books", bookList);


            return new ResponseEntity<>(res.toString(), HttpStatus.OK);

        } catch (Exception ex) {
            JSONObject res = new JSONObject();
            res.put("error_message", "not found " + ex.getMessage());
            return new ResponseEntity<>(res.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/api/students/{students_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudents1(@PathVariable("students_id") String students_id) throws JSONException {
        try {
            JSONObject res = new JSONObject();


            System.err.print("delete " + students_id);
            boolean students = this.studentsService.delete(students_id);

            if (students) {

                res.put("message", "Deleted");
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            }
            res.put("error_message", " not found ");
            return new ResponseEntity<>(res.toString(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            JSONObject res = new JSONObject();
            ex.printStackTrace();

            res.put("error_message", " ERROR MESSAGE " + ex.getMessage());
            return new ResponseEntity<>(res.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @PutMapping(path = "/api/students/{students_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudents(@ModelAttribute Students modelTO, @PathVariable("students_id") Integer students_id) throws JSONException {

        try {
            if (validation.isValidName(modelTO.getName()) == false) {

                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid name ");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if (validation.isValidSurname(modelTO.getSurname()) == false) {

                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid surname");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            if (validation.isValidPhone(modelTO.getPhone()) == false) {

                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid phone");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            System.err.println("name- " + modelTO.getName());
            System.err.println("surname - " + modelTO.getSurname());
            System.err.println("phone - " + modelTO.getPhone());
            System.err.println(students_id);


            Students students = new Students();

            students.setName(modelTO.getName());
            students.setSurname(modelTO.getSurname());
            students.setPhone(modelTO.getPhone());
            students.setId(students_id);

            int studentId = this.studentsService.update(students);
            JSONObject res = new JSONObject();
            Students students1 = this.studentsService.get(students_id);
            res.put("students_name", students1.getName());
            res.put("students_surname", students1.getSurname());
            res.put("students_phone", students1.getPhone());
            res.put("students_id", students1.getId());

            return new ResponseEntity<>(res.toString(), HttpStatus.OK);

        } catch (DuplicateKeyException ex) {
            JSONObject res = new JSONObject();
            res.put("error_message", "DUPLICATE ERROR MESSAGE " + ex.getMessage());
            return new ResponseEntity<>(res.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception ex) {
            JSONObject res = new JSONObject();
            res.put("error_message", "not found " + ex.getMessage());
            return new ResponseEntity<>(res.toString(), HttpStatus.NOT_FOUND);
        }


    }


}
