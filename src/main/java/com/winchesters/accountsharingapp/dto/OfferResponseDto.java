package com.winchesters.accountsharingapp.dto;

import com.winchesters.accountsharingapp.account.Account;
import com.winchesters.accountsharingapp.request.Request;
import com.winchesters.accountsharingapp.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseDto {
    private Long id;
    private Date uploadDate;
    private Double calculatedPrice;
    private Integer maxSplitters;
    private Boolean isPublic;
    private Long accountId;
    private String offerer;
    private List<String> splitterUsernames;
    private Integer numberOfRequests;
    private AccountResponseDto account;
}
