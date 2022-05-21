package com.winchesters.accountsharingapp.account.dto;

import com.winchesters.accountsharingapp.account.Credentials;

public record CreateAccountDto(
        String provider,
        String subscriptionType,
        Credentials credentials
) {
}
