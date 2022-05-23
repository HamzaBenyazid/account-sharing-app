package com.winchesters.accountsharingapp.payment.entity;

import lombok.Data;

@Data
public class ChargeRequest {
    private Currency currency;
    private String description;
    private int amount;
    private String stripeEmail;
    private String stripeToken;
}
