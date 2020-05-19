package com.sampath.RedisExample.controller;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/url")
public class UrlShortener {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable("id") String id) {
        return redisTemplate.opsForValue().get(id);
    }

    @PostMapping
    public String create(@RequestBody String url) {
        System.out.println("input : " + url);
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );

        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            redisTemplate.opsForValue().set(id, url);
            return id;
        }
        throw new RuntimeException("URL invalid: " + url);
    }
}
