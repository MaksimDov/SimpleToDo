package com.Maksim.SimpleToDo.repos;

import com.Maksim.SimpleToDo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    /**
     * Поиск пользователя в базе данных по логину или адресу почты
     * @param username
     * @param email
     * @return
     */
    User findByUsernameOrEmail(String username, String email);

    /**
     * Поиск пользователя в БД по логину
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * Поиск пользователя в БД по адресу почты
     * @param email
     * @return
     */
    User findByEmail(String email);

}
