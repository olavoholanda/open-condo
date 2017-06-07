package com.opencondo.accountservice.controller;

import com.opencondo.accountservice.model.entity.User;
import com.opencondo.accountservice.model.storage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Put a description of the class here.
 *
 * @author: Olavo Holanda
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(method=RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return repository.save(user);
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public void deleteUser(@RequestParam(value = "id") String id) {
        repository.deleteById(id);
    }

    @RequestMapping(method=RequestMethod.GET)
    public User getUser(@RequestParam(value = "id") String id) {
        Optional<User> opUser = repository.findById(id);
        if(opUser.isPresent()){
            return opUser.get();
        }
        return null;
    }

    @RequestMapping(value = "/all", method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
