package com.winchesters.accountsharingapp.mapper;

import com.winchesters.accountsharingapp.account.Account;
import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.dto.AccountResponseDto;
import com.winchesters.accountsharingapp.dto.OfferDto;
import com.winchesters.accountsharingapp.dto.UserResponseDto;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.user.User;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDtoMapper {
    public static UserResponseDto userToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
    public static OfferResponseDto offerToOfferResponseDto(Offer offer){
        OfferResponseDto offerResponseDto = new OfferResponseDto();
        offerResponseDto.setOfferer(offer.getOfferer().getUsername());
        offerResponseDto.setAccountId(offer.getAccount().getId());
        offerResponseDto.setId(offer.getId());
        offerResponseDto.setCalculatedPrice(offer.getCalculatedPrice());
        offerResponseDto.setUploadDate(offer.getUploadDate());
        offerResponseDto.setIsPublic(offer.getIsPublic());
//        offerResponseDto.setRequests(offer.getRequests());
        offerResponseDto.setMaxSplitters(offer.getMaxSplitters());
        offerResponseDto.setSplitterUsernames(offer.getSplitters().stream().map(User::getUsername).collect(Collectors.toList()));
        return offerResponseDto;
    }
    public static List<OfferResponseDto> offerToOfferResponseDto(List<Offer> offers){
        return offers.stream().map(EntityToDtoMapper::offerToOfferResponseDto).toList();
    }

        public static List<UserResponseDto> userToUserResponseDto(Collection<User> users) {
        return users.stream().map(EntityToDtoMapper::userToUserResponseDto).collect(Collectors.toList());
    }

    public static AccountResponseDto accountToAccountResponseDto(Account account){
        return new AccountResponseDto(
                account.getId(),
                account.getProvider().name(),
                account.getSubscriptionType(),
                account.getOwner().getUsername()
        );
    }
    public static List<AccountResponseDto> accountToAccountResponseDto(List<Account> accounts){
        return accounts.stream().map(EntityToDtoMapper::accountToAccountResponseDto).toList();
    }

    public static OfferDto OfferToOfferDto(Offer offer,Boolean owned) {
        return new OfferDto(
                offer.getId(),
                owned,
                offer.getAccount().getProvider(),
                offer.getAccount().getSubscriptionType(),
                offer.getMaxSplitters(),
                offer.getSplitters().size(),
                offer.getCalculatedPrice()
        );
    }

    public static List<OfferDto> OfferToOfferDto(List<Offer> offers,Boolean owned) {
        return offers.stream().map((offer -> OfferToOfferDto(offer,owned))).toList();
    }
}
