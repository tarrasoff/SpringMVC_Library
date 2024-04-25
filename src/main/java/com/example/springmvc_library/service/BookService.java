package com.example.springmvc_library.service;

import com.example.springmvc_library.entity.Book;
import com.example.springmvc_library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book updateBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));

        updateBook.setTitle(book.getTitle());
        updateBook.setYearPublished(book.getYearPublished());
        updateBook.setAuthors(book.getAuthors());

        return bookRepository.save(updateBook);
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }
}