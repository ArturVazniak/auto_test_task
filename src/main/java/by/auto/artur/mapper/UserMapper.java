package by.auto.artur.mapper;

import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.User;
import org.mapstruct.InheritConfiguration;
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

    @Mapping(source = "user.email", target = "emailDto", defaultValue = "emailDto")
    UserDto userToDto(User user);

    @Mapping(source = "user.email", target = "emailDto", defaultValue = "emailDto")
    List<UserDto> userListToDto(List<User> user);

    @InheritConfiguration
    User DtoFromUser(UserDto userDto);

}
