package com.example.bookstore.repos;

import com.example.bookstore.entities.Author;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
}
