package by.auto.artur.dto;

import lombok.Data;
import javax.persistence.Id;

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
    private String username;
    private String emailDto;
}
