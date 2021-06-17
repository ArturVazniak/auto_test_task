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

    @Id
    private long id;
    private String usernameDto;
    private String passwordDto;
    private String email;
    private Collection<Role> rolesDto;

}
