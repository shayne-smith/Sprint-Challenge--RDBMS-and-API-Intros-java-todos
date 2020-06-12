package com.lambdaschool.sprintchallenge.controllers;

import com.lambdaschool.sprintchallenge.models.Todo;
import com.lambdaschool.sprintchallenge.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/todos")
public class TodosController
{

    @Autowired
    TodoService todoService;

    @PostMapping(value = "/user/{userid}")
    public ResponseEntity<?> addNewTodo(
        @PathVariable
            String description,
        @PathVariable
            long userid
        ) throws URISyntaxException
    {
        Todo newTodo = todoService.save(userid, description);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTodoURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/todos/user/{userid}")
            .buildAndExpand(newTodo.getTodoid())
            .toUri();
        responseHeaders.setLocation(newTodoURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @PatchMapping(value = "/user/{userid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(
        @RequestBody
            String updatedDescription,
        @PathVariable
            long userid)
    {
        todoService.update(userid, updatedDescription);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
