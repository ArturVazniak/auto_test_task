package by.auto.artur.repository;

import by.auto.artur.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUsername(String username);
}
