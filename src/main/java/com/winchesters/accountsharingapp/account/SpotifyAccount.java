package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.spotify.SpotifySubscriptionFactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;

@DiscriminatorValue("SpotifyAccount")
@Entity
public class SpotifyAccount extends Account{
    public SpotifyAccount() {
        super(
                new SpotifySubscriptionFactory(),
                SPOTIFY
        );
    }
}
