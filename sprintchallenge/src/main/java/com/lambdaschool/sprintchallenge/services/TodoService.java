package com.lambdaschool.sprintchallenge.services;

import com.lambdaschool.sprintchallenge.models.Todo;

public interface TodoService
{
    Todo save(
        long userid,
        String description);

    Todo update(long id);

    Todo findTodoById(long id);

}
