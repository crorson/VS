package com.example.booklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(produces = "application/json")
    List<Book> getAll() {
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @GetMapping(path = "/{title}", produces = "application/json")
    Book getBook(@PathVariable String title) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        Book book = optionalBook.get();
        return book;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    Book addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    @PostMapping(path = "/{title}", produces = "application/json")
    Book addBook(@PathVariable String title) {
        Book book = new Book(title);
        bookRepository.save(book);
        return book;
    }

    @PostMapping(path = "/{title}/{author}", produces = "application/json")
    Book addBook(@PathVariable String title, @PathVariable String author) {
        Book book = new Book(title, author);
        bookRepository.save(book);
        return book;
    }

    @PutMapping(path = "/{oldTitle}/{newTitle}/{newAuthor}",  consumes = "application/json", produces = "application/json")
    Book updateBook(@PathVariable String oldTitle, @PathVariable String newTitle, @PathVariable String newAuthor) {
        Optional<Book> optionalBook = bookRepository.findByTitle(oldTitle);
        Book book = optionalBook.get();
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        return book;
    }

    @DeleteMapping(path = "/{title}", produces = "application/json")
    Book deleteBook(@PathVariable String title) {
        bookRepository.deleteByTitle(title);
        return null;
    }
}