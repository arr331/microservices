package com.feignclient.feignclient.client;

import com.feignclient.feignclient.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookHystrixFallbackFactory implements BookClient {

    @Override
    public Book getById(int id) {
        return new Book(999, "Ninguno", "jsajdsad");
    }
}
