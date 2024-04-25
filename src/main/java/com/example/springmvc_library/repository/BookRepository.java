package com.example.springmvc_library.repository;

import com.example.springmvc_library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}