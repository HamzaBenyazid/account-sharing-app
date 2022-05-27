package com.winchesters.accountsharingapp.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChargeRequestDto {
    private String description;
    private Integer amount;
    private String stripeEmail;
    private String stripeToken;
    public static ChargeRequest toChargeRequest(ChargeRequestDto chargeRequest){
        return new ChargeRequest(Currency.USD,chargeRequest.getDescription(),chargeRequest.getAmount(),chargeRequest.getStripeEmail(), chargeRequest.getStripeToken());
    }
}
