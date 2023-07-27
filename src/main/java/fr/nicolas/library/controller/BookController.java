package fr.nicolas.library.controller;

import fr.nicolas.library.entity.Book;
import fr.nicolas.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/library")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("books")
    public ResponseEntity<Book> post(@RequestBody Book book) {

        if(book.getTitle() == null) {
            return  ResponseEntity.badRequest().build();
        }

        Optional<Book> optionalBook = bookService.post(book);
        if(optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalBook.get());
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Book> put(@PathVariable Long id, @RequestBody Book book) {

        if(id != book.getId() ||
           book.getId() == null ||
           book.getTitle() == null) {
            ResponseEntity.badRequest().build();
        }

        Optional<Book> optionalBook = bookService.put(book);

        if(optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalBook.get());

    }

    @GetMapping("books")
    public ResponseEntity<List<Book>> getAll() {
        Optional<List<Book>> optionalBooks = bookService.getAll();

        if(optionalBooks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalBooks.get());
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getOne(Long id) {
        Optional<Book> optionalBook = bookService.getOneById(id);

        if(optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalBook.get());
    }

}
