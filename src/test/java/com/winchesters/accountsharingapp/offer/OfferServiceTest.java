package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    @Mock
    private  OfferRepository offerRepository;
    @Mock
    private  UserService userService;
    @Mock
    private  AuthenticationFacade authenticationFacade;

    @Test
    @DisplayName("Should find offer by id")
    void shouldFindOfferById() {
        OfferService  offerService =new OfferService(offerRepository,userService,authenticationFacade);

    }
}