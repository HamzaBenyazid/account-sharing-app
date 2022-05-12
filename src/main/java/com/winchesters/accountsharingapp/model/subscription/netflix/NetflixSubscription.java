package com.winchesters.accountsharingapp.model.subscription.netflix;

import com.winchesters.accountsharingapp.model.subscription.Subscription;
import lombok.AllArgsConstructor;


@AllArgsConstructor
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

    public Boolean getHdAvailable() {
        return hdAvailable;
    }

    public Boolean getUhdAvailable() {
        return uhdAvailable;
    }

    public Integer getScreens() {
        return screens;
    }
}
