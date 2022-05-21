package com.winchesters.accountsharingapp.dto;

import com.winchesters.accountsharingapp.account.Credentials;

public record CreateAccountDto(
        String provider,
        String subscriptionType,
        Credentials credentials
) {
}
