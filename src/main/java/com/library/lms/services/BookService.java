package com.library.lms.services;

import com.library.lms.exception.ResourceAlreadyExistException;
import com.library.lms.exception.ResourceNotFoundException;
import com.library.lms.model.Book;
import com.library.lms.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) throws ResourceAlreadyExistException {

        Optional<Book> optionalBook = bookRepository.findBookByIsbn(book.getIsbn());

        if (optionalBook.isPresent()) {
            throw new  ResourceAlreadyExistException("Book already exist in database with isbn number " + book.getIsbn());
        }

        return bookRepository.save(book);
    }

    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) throws ResourceNotFoundException {

        Optional<Book> optionalBook = bookRepository.findBookByIsbn(isbn);

        optionalBook.orElseThrow(() -> new ResourceNotFoundException("Book is not found with isbn " + isbn));

        return optionalBook.get();
    }

    public Book getBookByCategory(String category) throws ResourceNotFoundException {

        Optional<Book> optionalBook = bookRepository.findBookByCategory(category);

        optionalBook.orElseThrow(() -> new ResourceNotFoundException("Book is not found with category " + category));

        return optionalBook.get();
    }

    public Book updateBook(Book book, String isbn) throws ResourceNotFoundException {

        Optional<Book> optionalBook = bookRepository.findBookByIsbn(isbn);

        optionalBook.orElseThrow(() -> new ResourceNotFoundException("Book is not found with isbn " + isbn));

        return bookRepository.save(book);
    }

    public void deleteBook(String isbn) throws ResourceNotFoundException {

        Optional<Book> optionalBook = bookRepository.findBookByIsbn(isbn);

        optionalBook.orElseThrow(() -> new ResourceNotFoundException("Book is not found with isbn " + isbn));

        bookRepository.delete(optionalBook.get());
    }
}
