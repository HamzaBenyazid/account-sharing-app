package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    @Mock
    private  OfferRepository offerRepository;
    @Mock
    private  UserService userService;
    @Mock
    private  AuthenticationFacade authenticationFacade;

    @InjectMocks
    private OfferService offerService;

    @Test
    @DisplayName("Should find offer by id")
    void shouldFindOfferById() {
        //given
        Offer offer=new Offer(123L,null,100.0,3,true,null,null,null,null);
        //when
        Mockito.when(offerRepository.findById(123L)).thenReturn(Optional.of(offer));
        Offer actualOfferReturned = offerService.getOfferById(123L);
        //then
        Assertions.assertEquals(offer.getId(),actualOfferReturned.getId());

    }
}