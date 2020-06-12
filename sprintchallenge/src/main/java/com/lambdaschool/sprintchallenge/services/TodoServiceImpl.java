package com.lambdaschool.sprintchallenge.services;

import com.lambdaschool.sprintchallenge.models.Todo;
import com.lambdaschool.sprintchallenge.models.User;
import com.lambdaschool.sprintchallenge.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{

    @Autowired
    private TodoRepository todorepos;

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public Todo update(
        long todoid)
    {
        if (todorepos.findById(todoid)
            .isPresent())
        {
            Todo todo = findTodoById(todoid);
            todo.setCompleted(true);
            return todorepos.save(todo);
        } else
        {
            throw new EntityNotFoundException("Todo with id " + todoid + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Todo save(
        long userid,
        String description)
    {
        User currentUser = userService.findUserById(userid);

        Todo newTodo = new Todo(currentUser,
            description);
        return todorepos.save(newTodo);
    }

    public Todo findTodoById(long id) throws EntityNotFoundException
    {
        return todorepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Todo id " + id + " not found!"));
    }

}
