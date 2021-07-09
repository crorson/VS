package com.example.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class StarterApplication {

    ArrayList<String> bookList = new ArrayList<String>();

    @GetMapping ("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping ("/bookList")
    public String getBookList() {
        return bookList.toString();
    }

    @GetMapping ("/bookList/{id}")
    public String getBookById(@PathVariable int id) {
        String book = bookList.get(id).toString();
        return "Book with ID = " + id + ": " + book;
    }

    @GetMapping ("/bookList/{title}")
    public String getBookByTitle(@PathVariable String title) {
        int id = bookList.indexOf(title);
        String book = bookList.get(id).toString();
        return book;
    }

    @PostMapping (path= "/addBook/{newBook}")
    public String addBook(@PathVariable String newBook) {
        bookList.add(newBook);
        return newBook + " has been added to the list.";
    }

    @PutMapping ("/updateBook/{oldTitle}/{newTitle}")
    public String updateBook(@PathVariable String oldTitle, @PathVariable String newTitle) {
        int id = bookList.indexOf(oldTitle);
        bookList.set(id, newTitle);
        return "Updated Book with title: " + oldTitle + " to: " + newTitle;
    }

    @DeleteMapping ("/deleteBook/{title}")
    public String deleteBook(@PathVariable String title) {
        int id = bookList.indexOf(title);
        bookList.remove(id);
        return "Deleted Book with title: " + title;
    }

    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }

}
