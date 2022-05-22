package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.Account;
import com.winchesters.accountsharingapp.account.FakeAccount;
import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionFactory;
import com.winchesters.accountsharingapp.subscription.SubscriptionService;
import com.winchesters.accountsharingapp.subscription.fake.FakeSubscription;
import com.winchesters.accountsharingapp.user.User;
import com.winchesters.accountsharingapp.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Offer offer=new Offer(123L,null,null,3,true,null,null,null,null);
        //Account account = new FakeAccount();

        //when
        Mockito.when(offerRepository.findById(123L)).thenReturn(Optional.of(offer));
        Offer actualOfferReturned = offerService.getOfferById(123L);
        //then
        Assertions.assertEquals(offer.getId(),actualOfferReturned.getId());

    }

    @Test
    @DisplayName("Should save offer")
    void shouldSaveOffer() {
        //given
        Subscription subscription = new FakeSubscription();
        Account account = new FakeAccount();
        account.setSubscription(subscription);
        Offer offer=new Offer(123L,null,null,3,true,null,null,null,null);
        offer.setAccount(account);
        User user = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null,null,null,null,null,null);
        //when
        Mockito.when(userService.getUser()).thenReturn(user);
        offerService.createOffer(offer);
        //then
        Mockito.verify(offerRepository,Mockito.times(1)).save(ArgumentMatchers.any(Offer.class));
    }


    @Test
    @DisplayName("Should get offers by owner")
    void shouldGetOffersByOwner() {
        //given
        Offer offer=new Offer(123L,null,null,3,true,null,null,null,null);
        //when
        Mockito.when(offerRepository.findByOffererUsername(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Stream.of(offer,offer).collect(Collectors.toList())));
        Page<Offer> actualOffersReturned= offerService.getOffersByOfferer("meriem",1,1);
        //then
        Assertions.assertEquals(2L,actualOffersReturned.getTotalElements());
    }


    @Test
    @DisplayName("should update max splitters if valid")
    void shouldUpdateMaxSplitters() {
        //given
        Subscription subscription = new FakeSubscription();
        Account account = new FakeAccount();
        account.setSubscription(subscription);
        User user = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null,null,null,null,null,null);
        Offer offer=new Offer(123L,null,null,3,true,null,user,Stream.of(user,user).collect(Collectors.toList()), null);
        offer.setAccount(account);
        OfferResponseDto offerResponseDto = new OfferResponseDto(123L,null,null,3,true,account,user,null,null);
         //when
        Mockito.when(offerRepository.findById(123L)).thenReturn(Optional.of(offer));
        Mockito.when(authenticationFacade.getAuthenticatedUsername()).thenReturn(user.getUsername());
        //Mockito.when(EntityToDtoMapper.offerToOfferResponseDto(Mockito.any(Offer.class))).thenReturn(offerResponseDto);
        offerService.updateMaxSplitters(123L,5);
        //then
        Mockito.verify(offerRepository,Mockito.times(1)).save(ArgumentMatchers.any(Offer.class));

    }
}