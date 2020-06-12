package com.lambdaschool.sprintchallenge.controllers;

import com.lambdaschool.sprintchallenge.models.User;
import com.lambdaschool.sprintchallenge.services.UserService;
import com.lambdaschool.sprintchallenge.views.UserCountTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users",
        produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
            HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}",
        produces = {"application/json"})
    public ResponseEntity<?> getUserById(
        @PathVariable
            Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

    @PostMapping(value = "/user",
        consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(
        @Valid
        @RequestBody
            User newuser) throws URISyntaxException
    {
        newuser.setUserid(0);
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userid}")
            .buildAndExpand(newuser.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/userid/{userid}")
    public ResponseEntity<?> deleteUserById(
        @PathVariable
            long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/users/todos",
        produces = {"application/json"})
    public ResponseEntity<?> getIncompleteTodos()
    {
        List<UserCountTodos> u = userService.findIncompleteTodos();
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

}
