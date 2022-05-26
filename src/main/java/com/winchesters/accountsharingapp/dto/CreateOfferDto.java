package com.winchesters.accountsharingapp.dto;

public record CreateOfferDto(
    Long accountId,
    Integer maxSplitters
) {

}
