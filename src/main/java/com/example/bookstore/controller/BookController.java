package com.example.bookstore.controller;

import com.example.bookstore.repos.BookRepository;
import com.example.bookstore.entities.Book;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    private static final Log log = LogFactory.getLog(BookController.class);

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
    public String searchBook(@RequestParam(value = "title", required = false) String title, Model model) {
        log.info("title=" + title);
        if (title != null || !title.isEmpty()) {
            model.addAttribute("books", bookRepository.findByTitleContaining(title));
        } else {
            model.addAttribute("books", bookRepository.findAll());
        }
        return "index";
    }

}
