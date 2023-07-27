package fr.nicolas.library.controller;

import fr.nicolas.library.entity.Author;
import fr.nicolas.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/library")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("authors")
    public ResponseEntity<Author> post(@RequestBody Author author) {

        /*if(author.getName() == null) {
            return ResponseEntity.badRequest().build();
        }*/

        Optional<Author> optionalAuthor = authorService.post(author);
        if(optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalAuthor.get());

    }

    @PutMapping("authors/{id}")
    public ResponseEntity<Author> put(@PathVariable Long id, @RequestBody Author author) {

        if(id != author.getId() ||
           author.getId() == null ||
           author.getName() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Author> optionalAuthor = authorService.put(author);

        if(optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalAuthor.get());
    }

    @GetMapping("authors")
    public ResponseEntity<List<Author>> get() {
        Optional<List<Author>> optionalAuthors = authorService.getAll();

        if(optionalAuthors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(optionalAuthors.get());
    }

    @GetMapping("authors/{id}")
    public ResponseEntity<Author> getOne(@PathVariable Long id) {
        Optional<Author> optionalAuthor = authorService.getOneById(id);

        if(optionalAuthor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(optionalAuthor.get());
    }

}
