package by.auto.artur.mapper;

import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToDto(User user);

    List<UserDto> userListToDto(List<User> user);

    User DtoFromUser(UserDto userDto);

}
