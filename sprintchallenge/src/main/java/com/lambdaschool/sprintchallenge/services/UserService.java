package com.lambdaschool.sprintchallenge.services;

import com.lambdaschool.sprintchallenge.models.User;
import com.lambdaschool.sprintchallenge.views.UserCountTodos;

import java.util.List;

public interface UserService
{

    List<User> findAll();

    User findUserById(long id);

    User save(User user);

    void delete(long id);

    List<UserCountTodos> getUserCountTodos();

    //    void deleteTodo(long userid, long todoid);

    //    void addTodo(long userid, long todoid);

}
