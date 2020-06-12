package com.lambdaschool.sprintchallenge.repository;

import com.lambdaschool.sprintchallenge.models.User;
import com.lambdaschool.sprintchallenge.views.UserCountTodos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long>

    // SELECT user.username, COUNT(user.todos) as count
    // FROM users u
    // LEFT JOIN todos t
    // ORDER BY user.username

{
    @Query(value = "SELECT COUNT(*) as todocount FROM users u JOIN todos t", nativeQuery = true)
    UserCountTodos checkIncompleteTodos();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM todos WHERE userid = :userid", nativeQuery = true)
    void deleteUserTodos(long userid);

}
