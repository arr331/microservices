package com.feignclient.feignclient.service;

import com.feignclient.feignclient.client.BookClient;
import com.feignclient.feignclient.client.RatingClient;
import com.feignclient.feignclient.model.Book;
import com.feignclient.feignclient.model.BookInfo;
import com.feignclient.feignclient.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final BookClient bookClient;
    private final RatingClient ratingClient;

    @Autowired
    public ClientService(BookClient bookClient, RatingClient ratingClient) {
        this.bookClient = bookClient;
        this.ratingClient = ratingClient;
    }

    public BookInfo getById(int id) {
        Book book = bookClient.getById(id);
        Rating rating = ratingClient.getByBookId(id);
        return new BookInfo(book, rating);
    }
}
