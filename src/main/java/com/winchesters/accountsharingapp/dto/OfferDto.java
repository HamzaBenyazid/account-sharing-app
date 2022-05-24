package com.winchesters.accountsharingapp.dto;

import com.winchesters.accountsharingapp.account.AccountProvider;

public record OfferDto(

        Long id,
        Boolean owned,
        AccountProvider provider,
        String subscriptionType,
        Integer maxUsers,
        Integer numberOfSubscribers,
        Double price
        ) {
}
