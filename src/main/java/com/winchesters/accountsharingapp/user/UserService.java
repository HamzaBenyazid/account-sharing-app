package com.winchesters.accountsharingapp.user;

import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.exception.user.UserNotAuthenticatedException;
import com.winchesters.accountsharingapp.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    public User getUser() {
        String username = authenticationFacade.getAuthenticatedUsername();
        if(!username.equals("anonymousUser")) {
            return this.findUserByUsername(username);
        }
        throw new UserNotAuthenticatedException();
    }
    public User findUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));
    }
}
