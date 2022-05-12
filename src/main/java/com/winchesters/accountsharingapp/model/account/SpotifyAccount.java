package com.winchesters.accountsharingapp.model.account;

import com.winchesters.accountsharingapp.model.subscription.spotify.SpotifySubscription;

public class SpotifyAccount extends Account{
    public SpotifyAccount(SpotifySubscription subscription) {
        super(subscription);
    }
}
