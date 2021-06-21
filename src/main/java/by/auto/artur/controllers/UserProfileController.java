package by.auto.artur.controllers;

import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.Role;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.mapper.UserMapper;
import by.auto.artur.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;

    private boolean accessСheck(Principal principal, long id){
        User userPrincipal = userService.findUserByUsername(principal.getName());

        if(userPrincipal.getId() == id || userPrincipal.getRoles().stream().filter(r -> r.getName().
                equals("ADMIN")).findFirst().isPresent()){
            return true;
        }

        return false;
    }

    @GetMapping("/hello")
    public String hello (Principal principal){
        return "Hello " + principal.getName();
    }

    @PutMapping("/users/{userId}")
    public UserDto updateUser(@Valid @RequestBody User user, @PathVariable("userId") long userId, Principal principal){
        if(accessСheck(principal, userId)) {
            userService.saveUser(user);
            return userMapper.userToDto(user);
        }

        throw new NoSuchContentException("Access is denied");
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser( @PathVariable("userId") long userId, Principal principal) {
        if(accessСheck(principal, userId)) {
            userService.deleteUser(userId);
            return ResponseEntity.ok(String.format("Deletion of user '%s' was successful", principal.getName()));
        }

        throw new NoSuchContentException("Access is denied");
    }

}
