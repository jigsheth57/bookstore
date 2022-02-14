package com.example.bookstore.controller;

import com.example.bookstore.entities.Book;
import com.example.bookstore.repos.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @GetMapping("/{title}")
    public String getBook(Model model, @PathVariable String title) {
        model.addAttribute("book", bookRepository.findByTitle(title));
        return "book";
    }

    @PostMapping("/book-search")
    public String searchBook(@ModelAttribute("book") Book book) {
        System.out.println(book.getTitle());
        // model.addAttribute("book", bookRepository.findByTitle(title));
        return "book-search";
    }

}
