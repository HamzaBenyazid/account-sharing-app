package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.Account;
import com.winchesters.accountsharingapp.account.AccountRepository;
import com.winchesters.accountsharingapp.account.AccountService;
import com.winchesters.accountsharingapp.account.FakeAccount;
import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.dto.AccountResponseDto;
import com.winchesters.accountsharingapp.dto.CreateOfferDto;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.exception.offer.OfferNotEmptyException;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.request.Request;
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
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

@Captor
private ArgumentCaptor<Offer> offerArgumentCaptor;

    @InjectMocks
    private OfferService offerService;

    @Mock
    private AccountService accountService;

    @Mock
    private EntityToDtoMapper entityToDtoMapper;


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

//    @Test
//    @DisplayName("Should save offer")
//    void shouldSaveOffer() {
//        //given
//        Account account = new FakeAccount();
//        account.setSubscription(new FakeSubscription());
//        account.setId(1L);
//
//        User user = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null,null,null,null,null,null);
//
//        CreateOfferDto dto = new CreateOfferDto(1L,3);
//
//        OfferResponseDto offerResponseDto = new OfferResponseDto(1L,null,100.0,4,true,null,"meriem",null,4,EntityToDtoMapper.accountToAccountResponseDto(account));
//        //when
//        try (MockedStatic<EntityToDtoMapper> entityToDtoMapperMockedStatic =Mockito.mockStatic(EntityToDtoMapper.class)){
//            entityToDtoMapperMockedStatic.when(()->EntityToDtoMapper.offerToOfferResponseDto(Mockito.any(Offer.class))).thenReturn(offerResponseDto);
//        Mockito.when(userService.getUser()).thenReturn(user);
//        Mockito.when(accountService.findAccountById(1L)).thenReturn(account);
//        offerService.createOffer(dto);
//        //then
//        Mockito.verify(offerRepository,Mockito.times(1)).save(offerArgumentCaptor.capture());
//        Assertions.assertEquals(1L,offerArgumentCaptor.getValue().getOfferer().getId());
//        }
//        }


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


//    @Test
//    @DisplayName("should update max splitters if valid")
//    void shouldUpdateMaxSplitters() {
//        //given
//        Subscription subscription = new FakeSubscription();
//        Account account = new FakeAccount();
//        account.setSubscription(subscription);
//        User user = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null,null,null,null,null,null);
//        Offer offer=new Offer(123L,null,null,3,true,null,user,Stream.of(user,user).collect(Collectors.toList()), List.of(new Request(),new Request()));
//        offer.setAccount(account);
//        OfferResponseDto offerResponseDto = EntityToDtoMapper.offerToOfferResponseDto(offer);
//       //when
//        try (MockedStatic<EntityToDtoMapper> entityToDtoMapperMockedStatic =Mockito.mockStatic(EntityToDtoMapper.class)) {
//            entityToDtoMapperMockedStatic.when(() -> EntityToDtoMapper.offerToOfferResponseDto(Mockito.any(Offer.class))).thenReturn(offerResponseDto);
//            Mockito.when(offerRepository.findById(123L)).thenReturn(Optional.of(offer));
//        Mockito.when(authenticationFacade.getAuthenticatedUsername()).thenReturn(user.getUsername());
//        offerService.updateMaxSplitters(123L,5);
//        //then
//        Mockito.verify(offerRepository,Mockito.times(1)).save(ArgumentMatchers.any(Offer.class));
//
//    }}

    @Test
    @DisplayName("Should get all offers")
    void shouldGetAllOffers() {
        //given
        Offer offer=new Offer(123L,null,null,3,true,null,null,null,null);
        //when
        Mockito.when(offerRepository.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Stream.of(offer,offer).collect(Collectors.toList())));
        Page<Offer> actualOffersReturned=offerService.getOffers(null,4);
        //then
        Assertions.assertEquals(2L,actualOffersReturned.getTotalElements());
        Assertions.assertEquals(123L,actualOffersReturned.stream().collect(Collectors.toList()).get(1).getId());

    }


    @Test
    @DisplayName("Should throw exception when the offer has splitters")
    void shouldFailWhenOfferHasSplitters() {
        //given
        User user = new User(1L,"meriem","ouaziz","meriem@gmail.com","meriem","meriem",true,null,null,null,null,null,null,null);
        Offer offer=new Offer(123L,null,null,3,true,null,null,Stream.of(user,user,user).collect(Collectors.toList()), null);
        Long offerId = 123L;
        //when
        Mockito.when(offerRepository.findById(123L)).thenReturn(Optional.of(offer));
        //then
        OfferNotEmptyException offerNotEmptyException = assertThrows(OfferNotEmptyException.class,() -> {offerService.deteteOffer(123L);});
        assertTrue(offerNotEmptyException.getMessage().contains("offer with id " + offerId + " not empty."));
    }
}