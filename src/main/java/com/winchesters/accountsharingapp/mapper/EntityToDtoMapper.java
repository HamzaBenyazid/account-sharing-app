package com.winchesters.accountsharingapp.mapper;

import com.winchesters.accountsharingapp.dto.UserResponseDto;
import com.winchesters.accountsharingapp.user.User;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDtoMapper {
    public static UserResponseDto userToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
    public static List<UserResponseDto> userToUserResponseDto(Collection<User> users) {
        return users.stream().map(EntityToDtoMapper::userToUserResponseDto).collect(Collectors.toList());
    }
}
