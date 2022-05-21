package com.winchesters.accountsharingapp.mapper;

import com.winchesters.accountsharingapp.dto.UserResponseDto;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.offer.OfferResponseDto;
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
        offerResponseDto.setOfferer(offer.getOfferer());
        offerResponseDto.setAccount(offer.getAccount());
        offerResponseDto.setId(offer.getId());
        offerResponseDto.setCalculatedPrice(offer.getCalculatedPrice());
        offerResponseDto.setUploadDate(offer.getUploadDate());
        offerResponseDto.setIsPublic(offer.getIsPublic());
        offerResponseDto.setRequests(offer.getRequests());
        offerResponseDto.setMaxSplitters(offer.getMaxSplitters());
        return offerResponseDto;
    }
    public static List<UserResponseDto> userToUserResponseDto(Collection<User> users) {
        return users.stream().map(EntityToDtoMapper::userToUserResponseDto).collect(Collectors.toList());
    }
}
