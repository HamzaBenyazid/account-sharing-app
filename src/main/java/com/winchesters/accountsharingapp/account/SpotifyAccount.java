package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.spotify.SpotifySubscription;

public class SpotifyAccount extends Account{
    public SpotifyAccount(SpotifySubscription subscription) {
        super(subscription);
    }
}
