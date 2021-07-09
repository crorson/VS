package com.example.booklist;

import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    
    void deleteByTitle(String title);

    Optional<Book> findByTitle(String oldTitle);
}
