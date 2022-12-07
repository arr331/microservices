package com.book.book.controller;

import com.book.book.model.Book;
import com.book.book.model.BookInfo;
import com.book.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping(value = "/{id}")
    public BookInfo getById(@PathVariable("id") int id) {
        return bookService.getById(id);
    }

    @PostMapping
    public Book create() {
        return bookService.create();
    }
}
