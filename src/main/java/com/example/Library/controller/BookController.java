package com.example.library.controller;


import com.example.library.model.Book;
import com.example.library.model.BookResponseData;
import com.example.library.service.BookService;
import com.example.library.utill.Validation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class BookController {

    @Autowired
    private BookService bookService;

    private Validation validation = new Validation();

    @PostMapping(path = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBook(@ModelAttribute BookResponseData modelTO) throws JSONException {
        try {

            if (validation.isValidTitle(modelTO.getTitle()) == false) {
                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid title ");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if (validation.isValidAuthor(modelTO.getAuthor()) == false) {
                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid Author");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            if (validation.isValidQuantity(modelTO.getQuantity()) == false) {
                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid Quantity");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

            }

            System.err.println("Title - " + modelTO.getTitle());
            System.err.println("Author - " + modelTO.getAuthor());
            System.err.println("Quantity  - " + modelTO.getQuantity());


            Book book = new Book();

            book.setTitle(modelTO.getTitle());
            book.setAuthor(modelTO.getAuthor());
            book.setQuantity(modelTO.getQuantity());

            int bookId = this.bookService.add(book, modelTO.getStudentsId());
            Book book2 = bookService.get(bookId);
            System.err.println(book2);

            JSONObject res = new JSONObject();
            res.put("id", book2.getId());
            res.put("title", book2.getTitle());
            res.put("author", book2.getAuthor());
            res.put("quantity", book2.getQuantity());


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

    @GetMapping(path = "/api/book/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBook(@PathVariable("book_id") String book_id) throws JSONException {

        try {

            System.err.print("get  " + book_id);

            JSONObject res = new JSONObject();

            Book book1 = this.bookService.get(Integer.parseInt(book_id));
            res.put("book_title", book1.getTitle());
            res.put("book_author", book1.getAuthor());
            res.put("book_quantity", book1.getQuantity());
            res.put("book_id", book1.getId());

            return new ResponseEntity<>(res.toString(), HttpStatus.OK);

        } catch (Exception ex) {
            JSONObject res = new JSONObject();
            res.put("error_message", " not found  " + ex.getMessage());
            return new ResponseEntity<>(res.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(path = "/api/book/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> geteBook1(@PathVariable("book_id") String book_id) throws JSONException {
        try {
            JSONObject res = new JSONObject();


            System.err.print("delete " + book_id);
            boolean book = this.bookService.delete(book_id);

            if (book) {

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


    @PutMapping(path = "/api/book/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBook(@ModelAttribute BookResponseData modelTO, @PathVariable("book_id") Integer book_id) throws JSONException {

        try {
            if (validation.isValidTitle(modelTO.getTitle()) == false) {

                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid title ");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if (validation.isValidAuthor(modelTO.getAuthor()) == false) {

                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid author");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            if (validation.isValidQuantity(modelTO.getQuantity()) == false) {

                JSONObject res = new JSONObject();
                res.put("error_message", "Error Invalid quantity");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            System.err.println("title - " + modelTO.getTitle());
            System.err.println("author - " + modelTO.getAuthor());
            System.err.println("quantity - " + modelTO.getQuantity());
            System.err.println(book_id);


            Book book = new Book();

            book.setTitle(modelTO.getTitle());
            book.setAuthor(modelTO.getAuthor());
            book.setQuantity(modelTO.getQuantity());
            book.setId(book_id);
            System.err.println(modelTO.getStudentsId());
            int bookId = this.bookService.update(book, modelTO.getStudentsId());
            JSONObject res = new JSONObject();
            Book book1 = this.bookService.get(bookId);
            res.put("book_title", book1.getTitle());
            res.put("book_author", book1.getAuthor());
            res.put("book_quantity", book1.getQuantity());
            res.put("book_id", book.getId());


            return new ResponseEntity<>(res.toString(), HttpStatus.OK);

        } catch (DuplicateKeyException ex) {
            JSONObject res = new JSONObject();
            res.put("error_message", "DUPLICATE ERROR MESSAGE " + ex.getMessage());
            return new ResponseEntity<>(res.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject res = new JSONObject();
            res.put("error_message", "not found " + ex.getMessage());
            return new ResponseEntity<>(res.toString(), HttpStatus.NOT_FOUND);
        }


    }


}
