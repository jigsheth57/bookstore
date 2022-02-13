package com.example.bookstore.repos;

import java.util.List;

import com.example.bookstore.entities.Book;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);

    Book findByTitle(String title);
}
