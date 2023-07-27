package fr.nicolas.library.controller;


import fr.nicolas.library.entity.Category;
import fr.nicolas.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/library")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("categories")
    public ResponseEntity<Category> post(@RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.put(category);

        if(optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalCategory.get());
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<Category> put(@PathVariable Long id, @RequestBody Category category) {

        if(id != category.getId() ||
                category.getId() == null ||
                category.getType() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Category> optionalCategory = categoryService.put(category);

        if(optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalCategory.get());
    }


    @GetMapping("categories/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.getOneById(id);

        if(optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalCategory.get());
    }

    @GetMapping("categories")
    public ResponseEntity<List<Category>> get() {
        Optional<List<Category>> optionalCategories = categoryService.getAll();

        if(optionalCategories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalCategories.get());
    }


}
