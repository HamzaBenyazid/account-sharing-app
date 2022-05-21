package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.spotify.SpotifySubscriptionFactory;

public class SpotifyAccount extends Account{
    public SpotifyAccount() {
        super(
                new SpotifySubscriptionFactory(),
                AccountProvider.SPOTIFY
        );
    }
}
