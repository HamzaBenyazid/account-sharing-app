package com.winchesters.accountsharingapp.dto;

public record AccountResponseDto(
        Long id,
        String provider,
        String subscriptionType,
        String owner
) {
}
