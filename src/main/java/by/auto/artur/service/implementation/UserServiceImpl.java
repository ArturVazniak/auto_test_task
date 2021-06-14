package by.auto.artur.service.implementation;

import by.auto.artur.entity.User;
import by.auto.artur.repository.UserRepository;
import by.auto.artur.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * added check for exceptions and log (lombok)
 *
 *
 *@Author ArturVazniak
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        log.info("IN getAllUsers : {} users found", users.size());

        return users;
    }

    @Override
    public User saveUser(User user) {
        log.info("IN saveUser user: {} successfully registered", user.getUsername());
        repository.save(user);
        return user;
    }

    @Override
    public User getUser(long id) {
        User user = repository.findById(id).orElse(null);

        if(user == null){
            log.warn("IN getUserById - no user found by id {} ", id);
            return null;
        }
        log.info("IN getUserById user: {} found by id: {}", user, id);
        return user;
    }

    @Override
    public void deleteUser(long id) {
       User user = repository.findById(id).orElse(null);

        if(user != null){
            repository.deleteById(id);
            log.info("IN deleteUser user with id: {} successfully deleted", id);
        }
        log.warn("IN deleteUser - no user found by id {}", id);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = repository.findUserByUsername(username);

        if(user == null){
            log.warn("IN findUserByUsername user not found by username: {}", username);
            return null;
        }
        log.info("IN findUserByUsername user {} found by username: {}", user, username);
        return user;
    }
}
