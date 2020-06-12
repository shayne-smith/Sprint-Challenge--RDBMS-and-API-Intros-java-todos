package com.lambdaschool.sprintchallenge.repository;

import com.lambdaschool.sprintchallenge.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
}
