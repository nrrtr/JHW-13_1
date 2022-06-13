package ru.netology.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.products.Book;
import ru.netology.products.Product;
import ru.netology.products.Smartphone;
import ru.netology.products.exceptions.AlreadyExistsException;
import ru.netology.products.exceptions.NotFoundException;
import ru.netology.products.repository.Repository;

public class RepositoryTest {
    Repository repo = new Repository();
    Product p1 = new Product(1, "product", 1000);
    Product p2 = new Book(2, "book", 300, "author");
    Product p3 = new Smartphone(3, "maplePhone", 30_000, "maple");

    //.save()

    @Test
    void shouldSaveToEmptyRepo() {
        repo.save(p1);

        Product[] er = {p1};
        assertArrayEquals(er, repo.findAll());
    }

    @Test
    void shouldSaveToNonEmptyRepo() {
        repo.save(p1);
        repo.save(p2);
        repo.save(p3);

        Product[] er = {p1, p2, p3};
        assertArrayEquals(er, repo.findAll());
    }

    //.findAll()

    @Test
    void shouldFindEmptyRepo() {
        Product[] er = {};
        assertArrayEquals(er, repo.findAll());
    }

    @Test
    void shouldFindAllElementsInRepo() {
        repo.save(p1);
        repo.save(p2);

        Product[] er = {p1, p2};
        assertArrayEquals(er, repo.findAll());
    }

    //.removeById()

    @Test
    void shouldRemoveByIdInNonEmptyRepo() {
        repo.save(p1);
        repo.save(p2);
        repo.save(p3);
        repo.removeById(2);

        Product[] er = {p1,p3};
        assertArrayEquals(er, repo.findAll());
    }

    @Test
    void shouldNotRemoveByIdInNonEmptyRepo() {
        repo.save(p2);
        repo.save(p3);
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(1);
        });
    }
    @Test
    void shouldNotRemoveByIdInEmptyRepo() {
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(1234);
        });
    }

    @Test
    void shouldReturnAlreadyExistsException() {
        repo.save(p1);
        assertThrows(AlreadyExistsException.class, () -> {
            repo.save(p1);
        });
    }
}
