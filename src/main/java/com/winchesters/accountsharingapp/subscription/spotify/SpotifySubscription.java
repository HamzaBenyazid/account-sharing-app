package com.winchesters.accountsharingapp.subscription.spotify;

import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SpotifySubscription implements Subscription {

    private final Double price;
    private final Integer maxUsers;
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public Integer getMaxUsers() {
        return maxUsers;
    }
}
