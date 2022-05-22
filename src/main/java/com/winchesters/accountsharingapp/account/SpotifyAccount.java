package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.spotify.SpotifySubscriptionFactory;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;

public class SpotifyAccount extends Account{
    public SpotifyAccount() {
        super(
                new SpotifySubscriptionFactory(),
                SPOTIFY
        );
    }
}
