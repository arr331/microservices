package com.feignclient.feignclient.client;

import com.feignclient.feignclient.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rating-service", path = "/rating")
public interface RatingClient {

    @GetMapping(value = "/{id}")
    Rating getByBookId(@PathVariable("id") int id);
}
