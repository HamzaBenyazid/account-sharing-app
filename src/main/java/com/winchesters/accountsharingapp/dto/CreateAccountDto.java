package com.winchesters.accountsharingapp.dto;

import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.account.Credentials;

public record CreateAccountDto(
        AccountProvider provider,
        String subscriptionType,
        Credentials credentials) {
}
