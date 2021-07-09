package com.example.crudcontroller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
//@Configuration
//@EnableAutoConfiguration
public class CrudController {

    ArrayList<String> bookList = new ArrayList<String>();
    private Logger logger = LoggerFactory.getLogger(CrudController.class);

    @GetMapping ("/log")
    public String log() {
        logger.trace("This is a TRACE level message");
        logger.debug("This is a DEBUG level message");
        logger.info("This is an INFO level message");
        logger.warn("This is a WARN level message");
        logger.error("This is an ERROR level message");
        return "See the log for details";
    }

    @GetMapping ("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping ("/bookList")
    public String getBookList() {
        logger.info("Books being returned {}", bookList);
        return bookList.toString();
    }

//    @GetMapping ("/bookList/{id}")
//    public String getBookById(@PathVariable int id) {
//        String book = bookList.get(id).toString();
//        logger.info("Book being returned {}", book);
//        return "Book with ID = " + id + ": " + book;
//    }
//
//    @GetMapping ("/bookList/{title}")
//    public String getBookByTitle(@PathVariable String title) {
//        int id = bookList.indexOf(title);
//        String book = bookList.get(id).toString();
//        logger.info("Book being returned {}", book);
//        return book;
//    }

    @PostMapping ("/addBook/{newBook}")
    public String addBook(@PathVariable String newBook) {
        logger.error("Book to be added: {}", newBook);
        bookList.add(newBook);
        logger.debug("Current Book List: {}", bookList);
        return newBook + " has been added to the list.";
    }

    @PutMapping("/updateBook/{oldTitle}/{newTitle}")
    public String updateBook(@PathVariable String oldTitle, @PathVariable String newTitle) {
        logger.error("Book to be updated: {}", oldTitle);
        int id = bookList.indexOf(oldTitle);
        bookList.set(id, newTitle);
        logger.debug("Current List of Books: ", bookList);
        return "Updated Book with title: " + oldTitle + " to: " + newTitle;
    }

    @DeleteMapping ("/deleteBook/{title}")
    public String deleteBook(@PathVariable String title) {
        logger.error("Book to be deleted: {}", title);
        int id = bookList.indexOf(title);
        bookList.remove(id);
        logger.debug("Current List of Books: ", bookList);
        return "Deleted Book with title: " + title;
    }

}
