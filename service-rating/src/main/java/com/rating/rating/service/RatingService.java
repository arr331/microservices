package com.rating.rating.service;

import com.rating.rating.model.Rating;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {
    private Rating rating1 = new Rating(1, 1, 5);
    private Rating rating2 = new Rating(2, 2, 4);
    private Rating rating3 = new Rating(3, 3, 2);

    public Rating create() {
        return rating1;
    }

    public List<Rating> getAll() {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating1);
        ratingList.add(rating2);
        ratingList.add(rating3);
        return ratingList;
    }

    public Rating getByBokkId(int id) {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating1);
        ratingList.add(rating2);
        ratingList.add(rating3);
        return ratingList.get(id -1);
    }
}
