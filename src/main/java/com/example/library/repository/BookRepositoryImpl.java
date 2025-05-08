package com.example.library.repository;

import com.example.library.model.Book;
import java.util.*;

public class BookRepositoryImpl implements BookRepository {
    private Map<Long, Book> bookStore = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(currentId++);
        }
        bookStore.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(bookStore.get(id));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }

    @Override
    public void delete(Long id) {
        bookStore.remove(id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookStore.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }
} 