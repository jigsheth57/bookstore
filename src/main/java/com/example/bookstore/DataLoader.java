package com.example.bookstore;

import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.repos.AuthorRepository;
import com.example.bookstore.repos.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

        private final BookRepository bookRepository;
        private final AuthorRepository authorRepository;

        @Autowired
        public DataLoader(BookRepository bookRepository, AuthorRepository authorRepository) {
                this.bookRepository = bookRepository;
                this.authorRepository = authorRepository;
        }

        @Override
        public void run(String... args) throws Exception {
                Author auth1 = new Author("Joe Ide", "Mystery");
                Author auth2 = new Author("Michael Crichton", "Sci-Fi");
                Author auth3 = new Author("Michael Connelly", "Realistic Fiction");
                this.authorRepository.save(auth1);
                this.authorRepository.save(auth2);
                this.authorRepository.save(auth3);

                this.bookRepository.save(
                                new Book("The Goodbye Coast", auth1,
                                                "Raymond Chandler's iconic detective"));
                this.bookRepository.save(new Book("Jurassic Park", auth2,
                                "An astonishing technique for recovering and cloning dinosaur DNA has been discovered. Now humankinds most thrilling fantasies have come true."));
                this.bookRepository.save(new Book("State of Fear", auth2,
                                "how information is manipulated in the modern world. From the streets of Paris, to the glaciers of Antarctica, to the exotic and dangerous Solomon Islands,"));
                this.bookRepository.save(new Book("The Dark Hours", auth3,
                                "There’s chaos in Hollywood at the end of the New Year’s Eve countdown. Working her graveyard shift, LAPD detective Renée Ballard waits out the traditional rain of lead as hundreds of revelers shoot their guns into the air."));
        }

}
