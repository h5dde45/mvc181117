package ru.es.bookmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.es.bookmanager.model.Book;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
        LOGGER.info("Book successfully saved. Book details: " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        LOGGER.info("Book successfully update. Book details: " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book= session.load(Book.class,new Integer(id));
        if(book!=null){
            session.delete(book);
        }
        LOGGER.info("Book successfully delete. Book details: " + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book= session.load(Book.class,new Integer(id));
        LOGGER.info("Book successfully received. Book details: " + book);
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBook() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list= session.createQuery("from Book").list();

        for (Book book : list) {
            LOGGER.info("Book - " + book);
        }
        return list;
    }
}
