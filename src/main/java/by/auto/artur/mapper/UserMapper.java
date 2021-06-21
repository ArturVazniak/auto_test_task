package by.auto.artur.mapper;

import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.email", target = "email")
    UserDto userToDto(User user);

    @Mapping(source = "user.email", target = "email")
    List<UserDto> userListToDto(List<User> user);

    @Mapping(source = "userDto.email", target = "email")
    @Mapping(source = "userDto.password", target = "password")
    @Mapping(source = "userDto.roles", target = "roles")
    User DtoFromUser(UserDto userDto);

}
