package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.AccountService;
import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.security.ApplicationSecurityConfig;
import com.winchesters.accountsharingapp.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@WebMvcTest(controllers = OfferController.class)
public class OfferControllerTest {

    @MockBean
    private OfferService offerService;
    @Autowired
    private WebApplicationContext webApplicationContext;



    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Should List All Offers when making Get request to end point /api/v1/offer/all")
    void shouldListAllOffers() throws Exception {


    }
}



