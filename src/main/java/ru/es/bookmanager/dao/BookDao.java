package ru.es.bookmanager.dao;

import ru.es.bookmanager.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(int id);

    Book getBookById(int id);

    List<Book> listBook();

}
