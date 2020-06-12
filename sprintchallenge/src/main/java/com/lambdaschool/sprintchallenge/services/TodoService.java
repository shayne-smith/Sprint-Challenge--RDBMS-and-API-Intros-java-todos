package com.lambdaschool.sprintchallenge.services;

import com.lambdaschool.sprintchallenge.models.Todo;

public interface TodoService
{
    Todo save(long userid, String description);

    Todo update(long id, String description);

    Todo findTodoById(long id);

}
