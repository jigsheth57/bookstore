package com.example.bookstore.repos;

import java.util.List;

import com.example.bookstore.entities.Author;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
    List<Author> findByNameContaining(String name);
}
