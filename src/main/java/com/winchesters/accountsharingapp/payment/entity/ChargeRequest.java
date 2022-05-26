package com.winchesters.accountsharingapp.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChargeRequest {
    private Currency currency;
    private String description;
    private Integer amount;
    private String stripeEmail;
    private String stripeToken;
}
