package by.auto.artur.service;

import by.auto.artur.entity.User;

import java.util.List;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

public interface UserService {

     List<User> getAllUsers();

     User saveUser(User user);

     User getUser(long id);

     void deleteUser(long id);

     User findUserByUsername(String username);

}
