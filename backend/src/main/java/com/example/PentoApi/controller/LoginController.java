package com.example.PentoApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PentoApi.doa.User;
import com.example.PentoApi.service.LoginService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controller for Login actions. Keep this class easy to read.
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService = new LoginService();

    @GetMapping(value = "/user")
    public List<User> getUser() throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try{
            return loginService.getAllUsers();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Gets user info
     * return User
     */
    @GetMapping(value = "/user/{username}", consumes = "application/json", produces = "application/json")
    public User getUser(@PathVariable String username) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try{
            return loginService.getUser(username);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Adds a user to the database (sign-up)
     * param user - user being added
     * return
     */
    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public String postUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try
        {
            return loginService.postUser(user);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
