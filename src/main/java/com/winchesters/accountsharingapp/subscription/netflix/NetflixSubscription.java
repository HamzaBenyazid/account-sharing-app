package com.winchesters.accountsharingapp.subscription.netflix;

import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public abstract class NetflixSubscription implements Subscription {
    private Boolean hdAvailable;
    private Boolean uhdAvailable;
    private Integer screens;
    private Double price;

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public Integer getMaxUsers() {
        return this.screens;
    }

}
