package fr.nicolas.library.service;

import fr.nicolas.library.entity.Author;
import fr.nicolas.library.entity.Book;
import fr.nicolas.library.repository.BooKRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BooKRepository booKRepository;

    public Optional<Book> post(Book book) {
        return Optional.of(booKRepository.save(book));
    }

    public Optional<Book> getOneById(Long id) {
        return  booKRepository.findById(id);
    }

    public Optional<List<Book>> getAll() {
        return Optional.of(booKRepository.findAll());
    }

    public Optional<Book> put(Book book) {
        return Optional.of(booKRepository.save(book));
    }

    public Optional<Book> patch(Book book) {
        return Optional.of(booKRepository.save(book));
    }

    public Optional<Book> delete(Book book) {

        try {
            booKRepository.delete(book);
        } catch (OptimisticLockingFailureException e) {
            return Optional.empty();
        }
        return Optional.of(book);
    }
}
