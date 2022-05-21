package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.netflix.NetflixSubscriptionFactory;

public class NetflixAccount extends Account{

    public NetflixAccount() {
        super(
                new NetflixSubscriptionFactory(),
                AccountProvider.NETFLIX
        );
    }
}
