package com.example.bookstore.controller;

import com.example.bookstore.entities.Book;
import com.example.bookstore.repos.AuthorRepository;
import com.example.bookstore.repos.BookRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private static final Log log = LogFactory.getLog(BookController.class);

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String homePage(@ModelAttribute Book book, Model model) {
        log.debug("title=" + book.getTitle());
        if (book.getTitle() != null || !book.getTitle().isEmpty()) {
            model.addAttribute("books", bookRepository.findByTitleContaining(book.getTitle()));
        } else {
            model.addAttribute("books", bookRepository.findAll());
        }
        model.addAttribute("book", new Book());
        return "index";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute Book book, Model model) {
        log.debug("book=" + book);
        bookRepository.save(book);
        return homePage(model);
    }

    @GetMapping("/{title}")
    public String getBook(Model model, @PathVariable String title) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("book", bookRepository.findByTitle(title));
        return "book";
    }

    @GetMapping("/remove-book")
    public String deleteBook(Model model, @RequestParam Long id) {
        bookRepository.deleteById(id);
        return homePage(model);
    }

}
