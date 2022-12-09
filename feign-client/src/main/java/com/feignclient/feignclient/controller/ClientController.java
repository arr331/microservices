package com.feignclient.feignclient.controller;

import com.feignclient.feignclient.model.Book;
import com.feignclient.feignclient.model.BookInfo;
import com.feignclient.feignclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/{id}")
    public BookInfo getById(@PathVariable("id") int id) {
        return clientService.getById(id);
    }

    private Book defaultGreeting(String username) {
        Book book = new Book(999,  "None", "None");
        return book;
    }
}
