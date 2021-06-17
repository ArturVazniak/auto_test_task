package by.auto.artur.service;

import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;

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

     User getUser(long id);

     User saveUser(User user);

     void deleteUser(long id);

     User findUserByUsername(String username);

     User registerNewUserAccount(UserDto userDto) throws NoSuchContentException;
}
