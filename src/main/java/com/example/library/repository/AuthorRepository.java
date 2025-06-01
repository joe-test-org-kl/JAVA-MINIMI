package com.example.library.repository;

import com.example.library.model.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(Long id);
    List<Author> findAll();
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    List<Author> findByNationality(String nationality);
    List<Author> findActiveAuthors();
    void delete(Long id);
    boolean existsById(Long id);
} 