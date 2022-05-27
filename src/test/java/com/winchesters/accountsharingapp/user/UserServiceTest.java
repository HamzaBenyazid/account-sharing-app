package com.winchesters.accountsharingapp.user;

import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.dto.SignUpFormDto;
import com.winchesters.accountsharingapp.dto.UserResponseDto;
import com.winchesters.accountsharingapp.exception.user.InvalidEmailException;
import com.winchesters.accountsharingapp.exception.user.UserNotFoundException;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.security.ApplicationUserRole;
import com.winchesters.accountsharingapp.security.PasswordConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.winchesters.accountsharingapp.security.ApplicationUserRole.ADMIN;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  AuthenticationFacade authenticationFacade;

    @InjectMocks
    private UserService userService;
    @Captor
    private  ArgumentCaptor<User> userArgumentCaptor;

    @Test
    @DisplayName("Should find user by username")
    void shouldFindUSerByUsername() {
        //given
        User expectedUser = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null,null,null,null,null,null);
        //when
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(expectedUser));
        //then
        User actualUserReturned = userService.findUserByUsername("meriem");
        Assertions.assertEquals(expectedUser.getId(),actualUserReturned.getId());

    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void shouldFailWhenUserNotFound() {
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class,()->{userService.findUserByUsername("chaimaa");});
        assertTrue(userNotFoundException.getMessage().contains("user wih username chaimaa not found"));
    }

    @Test
    @DisplayName("Should save the user")
    void shouldCreateUser() {
        //given
        User user = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null, ADMIN,null,null,null,null);
        UserResponseDto expectedUserResponseDto = EntityToDtoMapper.userToUserResponseDto(user);
        //when
        try (MockedStatic<EntityToDtoMapper> entityToDtoMapperMockedStatic =Mockito.mockStatic(EntityToDtoMapper.class)){
            entityToDtoMapperMockedStatic.when(()->EntityToDtoMapper.userToUserResponseDto(Mockito.any(User.class))).thenReturn(expectedUserResponseDto);
        userService.adminCreateUser(user);
        //then
        Mockito.verify(userRepository,Mockito.times(1)).save(userArgumentCaptor.capture());
        Assertions.assertEquals(1L,userArgumentCaptor.getValue().getId());
    }}

    @Test
    @DisplayName("Should save new user")
    void shouldSaveNewUser() {
        //given
        SignUpFormDto signUpFormDto = new SignUpFormDto("meriem","ouaziz","meriem","email@gmail.com","meriem");
        UserResponseDto expectedUserResponseDto = new UserResponseDto(1L,"meriem","email@gmail.com","CONTRIBUTOR");
        //when
        try (MockedStatic<PasswordConfig> configMockedStatic =Mockito.mockStatic(PasswordConfig.class)) {
            configMockedStatic.when(() -> PasswordConfig.isValid("meriem")).thenReturn(true);
            try(MockedStatic<EntityToDtoMapper> entityToDtoMapperMockedStatic = Mockito.mockStatic(EntityToDtoMapper.class)){
                entityToDtoMapperMockedStatic.when(()->EntityToDtoMapper.userToUserResponseDto(Mockito.any(User.class))).thenReturn(expectedUserResponseDto);
                userService.singUp(signUpFormDto);
                //then
                Mockito.verify(userRepository,Mockito.times(1)).save(userArgumentCaptor.capture());
                Assertions.assertEquals(expectedUserResponseDto.getEmail(),userArgumentCaptor.getValue().getEmail());
            }
        }

    }

    @Test
    @DisplayName("Should throw an exception if email is taken")
    void shouldFailIfEmailIsTaken() {
        //given
        SignUpFormDto signUpFormDto = new SignUpFormDto("meriem","ouaziz","meriem","email@gmail.com","meriem");
        User user = new User();
        //when
        Mockito.when(userRepository.findByEmail("email@gmail.com")).thenReturn(Optional.of(user));
        //then
        InvalidEmailException invalidEmailException = assertThrows(InvalidEmailException.class,()->userService.singUp(signUpFormDto));

    }

    @Test
    @DisplayName("Should update email")
    void shouldUpdateEmail() {
        //given
        User user = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null,null,null,null,null,null);
        //when
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //then
        userService.updateEmail(1L,"newemail");
        Mockito.verify(userRepository,Mockito.times(1)).save(userArgumentCaptor.capture());
        Assertions.assertEquals("newemail",userArgumentCaptor.getValue().getEmail());

    }
}