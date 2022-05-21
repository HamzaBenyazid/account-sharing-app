package com.winchesters.accountsharingapp.subscription.spotify;

import com.winchesters.accountsharingapp.subscription.Subscription;

public abstract class SpotifySubscription implements Subscription {
    @Override
    public Double getPrice() {
        return null;
    }

    @Override
    public Integer getMaxUsers() {
        return null;
    }
}
