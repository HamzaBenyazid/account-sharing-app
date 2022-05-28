package com.winchesters.accountsharingapp.dto;

import com.winchesters.accountsharingapp.account.Credentials;

public record AccountResponseDto(
        Long id,
        String provider,
        String subscriptionType,
        String owner,
        Credentials credentials
) {
}
