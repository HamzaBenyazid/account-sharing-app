package com.winchesters.accountsharingapp.request;

import com.winchesters.accountsharingapp.exception.offer.OfferNotEmptyException;
import com.winchesters.accountsharingapp.exception.request.InvalidRequesterException;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.offer.OfferRepository;
import com.winchesters.accountsharingapp.offer.OfferService;
import com.winchesters.accountsharingapp.user.User;
import com.winchesters.accountsharingapp.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RequestServiceTest {
    @Mock
    private  RequestRepository requestRepository;
    @Mock
    private  UserService userService;
    @Mock
    private  OfferService offerService;
    @Mock
    private  OfferRepository offerRepository;
    @InjectMocks
    RequestService requestService;

    @Captor
    ArgumentCaptor<Request> requestArgumentCaptor;

    @Captor
    ArgumentCaptor<Offer> offerArgumentCaptor;

    @Test
    @DisplayName("Should create request")
    void shouldCreateRequest() {
        //given
        User user = new User();
        Offer offer = new Offer();
        offer.setId(12L);
        //when
        Mockito.when(userService.getUser()).thenReturn(user);
        Mockito.when(offerService.getOfferById(Mockito.anyLong())).thenReturn(offer);
        //then
        requestService.createRequest(12L);
        Mockito.verify(requestRepository,Mockito.times(1)).save(requestArgumentCaptor.capture());
        Assertions.assertEquals(12L,requestArgumentCaptor.getValue().getOffer().getId());
    }

    @Test
    @DisplayName("Should throw an exception if user makes request to self")
    void shouldFailIfUserMakesRequestToSelf() {
        //given
        User user = new User();
        Offer offer = new Offer();
        offer.setId(1L);
        user.setOfferings(Stream.of(offer).collect(Collectors.toList()));
        //when
        Mockito.when(userService.getUser()).thenReturn(user);
        //then
        InvalidRequesterException invalidRequesterException = assertThrows(InvalidRequesterException.class,() -> {requestService.createRequest(1L);});
    }

    @Test
    @DisplayName("Should get requests made by user")
    void shouldGetRequestsForUser() {
        //given
        User user = new User();
        user.setUsername("meriem");
        //when
        Mockito.when(userService.getUser()).thenReturn(user);
        Mockito.when(requestRepository.findAllByRequesterUsername("meriem")).thenReturn(List.of(new Request(1L,null,null,null,user,null),new Request(2L,null,null,null,user,null)));
        //then
        List<Request> actualRequestsReturned = requestService.getRequestsToUser();
        Assertions.assertEquals(2,actualRequestsReturned.size());
        Assertions.assertEquals(1L,actualRequestsReturned.get(0).getId() );
    }

    @Test
    @DisplayName("Should set request state to DENIED")
    void shouldDenyResquest() {
        //given
        Request request = new Request(1L,null,RequestStatus.WAITING,null,null,null);
        //when
        Mockito.when(requestRepository.findById(request.getId())).thenReturn(Optional.of(request));
        requestService.denyRequest(1L);
        //then
        Mockito.verify(requestRepository,Mockito.times(1)).save(requestArgumentCaptor.capture());
        Assertions.assertEquals(RequestStatus.DENIED,requestArgumentCaptor.getValue().getStatus());

    }


    @Test
    @DisplayName("Should set request status to ACCEPTED")
    void shouldAcceptRequest() {
        //given
        Offer offer=new Offer(123L,null,null,3,true,null,null, List.of(new User(),new User()),null);
        Request request = new Request(1L,null,RequestStatus.WAITING,null,new User(1L,"meriem",null,null,null,null,null,null,null,null,null,null ,null,null),offer);
        //when
        Mockito.when(requestRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(request));
        Mockito.when(offerService.getOfferById(Mockito.anyLong())).thenReturn(offer);
        requestService.acceptRequest(1L);
        //then
        Mockito.verify(requestRepository,Mockito.times(1)).save(requestArgumentCaptor.capture());
        Mockito.verify(offerRepository,Mockito.times(1)).save(offerArgumentCaptor.capture());
        Assertions.assertEquals(RequestStatus.ACCEPTED,requestArgumentCaptor.getValue().getStatus());
        Assertions.assertEquals(1L,offerArgumentCaptor.getValue().getSplitters().get(offerArgumentCaptor.getValue().getSplitters().size()-1).getId());

    }
}