package fr.nicolas.library.service;

import fr.nicolas.library.entity.Book;
import fr.nicolas.library.entity.Category;
import fr.nicolas.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Optional<Category> post(Category category) {
        return Optional.of(categoryRepository.save(category));
    }

    public Optional<Category> getOneById(Long id) {
        return  categoryRepository.findById(id);
    }

    public Optional<List<Category>> getAll() {
        return Optional.of(categoryRepository.findAll());
    }

    public Optional<Category> put(Category category) {
        return Optional.of(categoryRepository.save(category));
    }

    public Optional<Category> patch(Category category) {
        return Optional.of(categoryRepository.save(category));
    }

    public Optional<Category> delete(Category category) {

        try {
            categoryRepository.delete(category);
        } catch (OptimisticLockingFailureException e) {
            return Optional.empty();
        }
        return Optional.of(category);
    }

}
