package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.netflix.NetflixSubscriptionFactory;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;

public class NetflixAccount extends Account{

    public NetflixAccount() {
        super(
                new NetflixSubscriptionFactory(),
                NETFLIX
        );
    }
}
