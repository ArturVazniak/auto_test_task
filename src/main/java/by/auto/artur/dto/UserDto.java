package by.auto.artur.dto;

import by.auto.artur.entity.Role;
import lombok.Data;
import javax.persistence.Id;
import java.util.Collection;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@Data
public class UserDto {

    private long id;
    private String username;
    private String password;
    private String email;
    private Collection<Role> roles;

}
