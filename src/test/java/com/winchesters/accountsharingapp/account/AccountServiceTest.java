package com.winchesters.accountsharingapp.account;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.winchesters.accountsharingapp.dto.CreateAccountDto;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.subscription.SubscriptionService;
import com.winchesters.accountsharingapp.user.User;
import com.winchesters.accountsharingapp.user.UserService;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private UserService userService;
    @InjectMocks
    private AccountService accountService;
    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Test
    @DisplayName("Should create Account")
    void shouldCreateAccount() {
        //given
        CreateAccountDto dto = new CreateAccountDto(AccountProvider.NETFLIX, "PREMIUM", null);
        Account account = new FakeAccount();
        account.setId(1L);
        //when
        try (MockedStatic<AccountFactory> accountFactoryMockedStatic = Mockito.mockStatic(AccountFactory.class)) {
            accountFactoryMockedStatic.when(
                            () -> AccountFactory.createAccount(Mockito.any())
                    )
                    .thenReturn(account);
            accountService.createAccount(dto);
            //then
            Mockito.verify(accountRepository, Mockito.times(1)).save(accountArgumentCaptor.capture());
            Assertions.assertEquals(1L, accountArgumentCaptor.getValue().getId());
        }
    }
}