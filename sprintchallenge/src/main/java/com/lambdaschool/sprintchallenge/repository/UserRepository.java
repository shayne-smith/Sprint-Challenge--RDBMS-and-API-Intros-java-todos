package com.lambdaschool.sprintchallenge.repository;

import com.lambdaschool.sprintchallenge.models.User;
import com.lambdaschool.sprintchallenge.views.UserCountTodos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long>
{
    @Query(value = "SELECT user.username, count(*) as count FROM users u JOIN todos t ON u.userid = t.userid WHERE !user.getTodos().isCompleted AND user.getTodos().size() > 0 ORDER BY u.username", nativeQuery = true)
    UserCountTodos checkCompletedTodos();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM todos WHERE userid = :userid", nativeQuery = true)
    void deleteUserTodos(long userid);

}
