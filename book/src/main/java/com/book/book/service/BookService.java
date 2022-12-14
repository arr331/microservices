package com.book.book.service;

import com.book.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private Book book1 = new Book(1, "Don Quijote de la Mancha", "Miguel de Cervantes");
    private Book book2 = new Book(2, "Viaje al fin de la noche", "Louis-Ferdinand Céline");
    private Book book3 = new Book(3, "Los cuentos de Canterbury", "Geoffrey Chaucer");
    private List<Book> bookList = new ArrayList<>();
    public Book create() {
        return book1;
    }

    public List<Book> getAll() {
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        return bookList;
    }

    public Book getById(int id) {
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        return bookList.get(id - 1);
    }
}
