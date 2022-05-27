package com.winchesters.accountsharingapp.user;

import com.winchesters.accountsharingapp.auth.ApplicationUserService;
import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.offer.OfferService;
import com.winchesters.accountsharingapp.security.ApplicationSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @MockBean
    private UserService userService;
    @MockBean
    private ApplicationUserService applicationUserService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Users when making Get request to end point /api/v1/user/users")
    public void shouldListAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/users"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

}
