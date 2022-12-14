package com.rating.rating.controller;

import com.rating.rating.model.Rating;
import com.rating.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<Rating> getAll() {
        return ratingService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Rating getByBookId(@PathVariable("id") int id) {
        return ratingService.getByBookId(id);
    }

    @PostMapping
    public Rating create() {
        return ratingService.create();
    }
}
