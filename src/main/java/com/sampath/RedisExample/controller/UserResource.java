package com.sampath.RedisExample.controller;

import com.sampath.RedisExample.models.User;
import com.sampath.RedisExample.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user/")
public class UserResource {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/add/{id}/{name}")
    public User add(@PathVariable("id") String id, @PathVariable("name") String name) {
        userRepo.save(new User(id, name, 10000L));
        return userRepo.findById(id);
    }


    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") String id, @PathVariable("name") String name) {
        userRepo.save(new User(id, name, 20000L));
        return userRepo.findById(id);
    }

    @GetMapping("/delete/{id}")
    public Map<String, User> update(@PathVariable("id") String id) {
        userRepo.delete(id);
        return getAll();
    }


    @GetMapping("/getall")
    public Map<String, User> getAll() {
        return userRepo.findAll();
    }
}
