package com.library.lms.controller;

import com.library.lms.exception.ResourceAlreadyExistException;
import com.library.lms.exception.ResourceNotFoundException;
import com.library.lms.model.Book;
import com.library.lms.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody Book book) {

        try {

            return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);

        } catch (ResourceAlreadyExistException raee) {
            return new ResponseEntity<>(raee.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getBookList() {

        try {

            return new ResponseEntity<>(bookService.getBookList(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {

        try {

            return new ResponseEntity<>(bookService.getBookByIsbn(isbn), HttpStatus.OK);

        } catch (ResourceNotFoundException rnfe) {
            return new ResponseEntity<>(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-category/{category}")
    public ResponseEntity<?> getBookByCategory(@PathVariable String category) {

        try {

            return new ResponseEntity<>(bookService.getBookByCategory(category), HttpStatus.OK);

        } catch (ResourceNotFoundException rnfe) {
            return new ResponseEntity<>(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{isbn}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable String isbn) {

        try {

            return new ResponseEntity<>(bookService.updateBook(book, isbn), HttpStatus.OK);

        } catch (ResourceNotFoundException rnfe) {
            return new ResponseEntity<>(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {

        try {

            bookService.deleteBook(isbn);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ResourceNotFoundException rnfe) {
            return new ResponseEntity<>(rnfe.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
