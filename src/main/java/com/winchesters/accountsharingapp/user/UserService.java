package com.winchesters.accountsharingapp.user;


import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.dto.SignUpFormDto;
import com.winchesters.accountsharingapp.dto.UserResponseDto;
import com.winchesters.accountsharingapp.exception.user.InvalidEmailException;
import com.winchesters.accountsharingapp.exception.user.InvalidUsernameException;
import com.winchesters.accountsharingapp.exception.user.UserNotAuthenticatedException;
import com.winchesters.accountsharingapp.exception.user.UserNotFoundException;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.security.PasswordConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static com.winchesters.accountsharingapp.security.ApplicationUserRole.CONTRIBUTOR;


@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,  AuthenticationFacade authenticationFacade) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationFacade = authenticationFacade;
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));
    }

    public List<UserResponseDto> getUsers() {
        return EntityToDtoMapper.userToUserResponseDto(userRepository.findAll());
    }

    public UserResponseDto adminCreateUser(User user) {

        return EntityToDtoMapper.userToUserResponseDto(userRepository.save(user));
    }

    public UserResponseDto singUp(SignUpFormDto signUpForm) {
        userRepository.findByUsername(signUpForm.getUsername())
                .ifPresent(
                        (u)-> {
                            throw new InvalidUsernameException(String.format("username %s is already taken.",u.getUsername()));
                        });
        userRepository.findByEmail(signUpForm.getEmail())
                .ifPresent(
                        (u)-> {
                            throw new InvalidEmailException(String.format("email %s is already taken.",u.getEmail()));
                        });
        String password = signUpForm.getPassword();

        if(!PasswordConfig.isValid(password))
            throw new IllegalStateException("password must contain ..TODO.....");

        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(signUpForm.getUsername());
        //TODO: add email validation
        user.setEmail(signUpForm.getEmail());
        user.setRole(CONTRIBUTOR);

        return EntityToDtoMapper.userToUserResponseDto(userRepository.save(user));
    }
    @Transactional
    public void updateEmail(Long userId, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(String.format("user with id %d not found",userId)));
        user.setEmail(email);
    }

    public Map<String,String> getAuthenticatedUser() {
        return Map.of(
                "username",authenticationFacade.getAuthenticatedUsername(),
                "role",authenticationFacade.getAuthenticatedUserRole()
        );
    }

    public User getUser(){
        String username = authenticationFacade.getAuthenticatedUsername();

        if(!username.equals("anonymousUser")) {
            return findUserByUsername(username);
        }

        throw new UserNotAuthenticatedException();
    }
}