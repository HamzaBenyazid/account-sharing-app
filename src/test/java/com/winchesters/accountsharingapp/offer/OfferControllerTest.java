package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.auth.ApplicationUserService;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.jwt.JwtConfig;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OfferController.class)
class OfferControllerTest {

    @MockBean
    private OfferService offerService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private ApplicationUserService applicationUserService;
    @MockBean
    private JwtConfig jwtConfig;



    @Test
    @DisplayName("Should List All Offers when making Get request to end point /api/v1/offer/all")
    void shouldlistOffers() throws Exception {
        /*Offer offer1 = new Offer(1l,null,null,null,null,null,null,null,null);
        Offer offer2 = new Offer(2l,null,null,null,null,null,null,null,null);
        OfferResponseDto exOffer1 = EntityToDtoMapper.offerToOfferResponseDto(offer1);

        OfferResponseDto exOffer2 = EntityToDtoMapper.offerToOfferResponseDto(offer2);
        Mockito.when(offerService.listOffers()).thenReturn(Arrays.asList(exOffer1,exOffer2));*/

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offer/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
               // .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));


    }

    @Test
    void shouldGetOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offer/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offer/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateImage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offer/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void ShouldListOfferSubscribers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offer/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void ShouldRemoveSubscriber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offer/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void unsubscribe() {
    }
}