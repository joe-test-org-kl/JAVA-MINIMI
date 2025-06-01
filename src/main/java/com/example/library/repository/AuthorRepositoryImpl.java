package com.example.library.repository;

import com.example.library.model.Author;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final Map<Long, Author> authors = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(idGenerator.getAndIncrement());
        }
        authors.put(author.getId(), author);
        return author;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(authors.get(id));
    }

    @Override
    public List<Author> findAll() {
        return new ArrayList<>(authors.values());
    }

    @Override
    public List<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return authors.values().stream()
                .filter(author -> author.getFirstName().equalsIgnoreCase(firstName) && 
                                author.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> findByNationality(String nationality) {
        return authors.values().stream()
                .filter(author -> nationality.equalsIgnoreCase(author.getNationality()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> findActiveAuthors() {
        return authors.values().stream()
                .filter(Author::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        authors.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return authors.containsKey(id);
    }
} 