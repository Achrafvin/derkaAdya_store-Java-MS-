package ma.store.userservice.mappers.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.userservice.mappers.Mapper;
import ma.store.userservice.models.dto.UserDto;
import ma.store.userservice.models.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    private final ModelMapper mapper;
    @Override
    public User mapTo(UserDto userDto) {
        return mapper.map(userDto,User.class);
    }

    @Override
    public UserDto mapFrom(User user) {
        return mapper.map(user, UserDto.class);
    }
}
