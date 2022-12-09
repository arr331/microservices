package com.feignclient.feignclient.client;

import com.feignclient.feignclient.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", fallback = BookHystrixFallbackFactory.class)
public interface BookClient {

    @GetMapping(value = "/book/{id}")
    public Book getById(@PathVariable("id") int id);
}
