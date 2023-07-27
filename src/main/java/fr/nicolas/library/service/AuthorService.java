package fr.nicolas.library.service;

import fr.nicolas.library.entity.Author;
import fr.nicolas.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public Optional<Author> post(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    public Optional<Author> getOneById(Long id) {
        return  authorRepository.findById(id);
    }

    public Optional<List<Author>> getAll() {
        return Optional.of(authorRepository.findAll());
    }

    public Optional<Author> put(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    public Optional<Author> patch(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    public Optional<Author> delete(Author author) {

        try {
            authorRepository.delete(author);
        } catch (OptimisticLockingFailureException e) {
            return Optional.empty();
        }
        return Optional.of(author);
    }

}
