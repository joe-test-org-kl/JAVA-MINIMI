package com.example.library.repository;

import com.example.library.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void delete(Long id);
    Optional<Book> findByIsbn(String isbn);
} 