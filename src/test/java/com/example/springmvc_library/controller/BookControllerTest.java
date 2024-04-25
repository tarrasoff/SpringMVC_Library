package com.example.springmvc_library.controller;

import com.example.springmvc_library.entity.Book;
import com.example.springmvc_library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetAllBooks() {
        Book book1 = Book.builder().id(1L).title("Title 1").yearPublished(2022).build();
        Book book2 = Book.builder().id(2L).title("Title 2").yearPublished(2023).build();
        Book book3 = Book.builder().id(3L).title("Title 3").yearPublished(2024).build();

        Page<Book> page = new PageImpl<>(List.of(book1, book2, book3));

        when(bookService.getAllBooks(any())).thenReturn(page);

        Page<Book> result = bookController.getAllBooks(PageRequest.of(0, 3)).getBody();

        assertEquals(3, result.getContent().size());
        assertEquals("Title 1", result.getContent().get(0).getTitle());
        assertEquals("Title 2", result.getContent().get(1).getTitle());
        assertEquals("Title 3", result.getContent().get(2).getTitle());
    }
}