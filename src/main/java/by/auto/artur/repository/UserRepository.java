package by.auto.artur.repository;

import by.auto.artur.entity.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

public interface UserRepository extends JpaRepository<User, Long> {

     User findUserByUsername(String username);
}
