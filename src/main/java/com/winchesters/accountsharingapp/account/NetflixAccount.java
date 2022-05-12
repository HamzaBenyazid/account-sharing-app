package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.netflix.NetflixSubscription;

public class NetflixAccount extends Account{

    public NetflixAccount(NetflixSubscription subscription) {
        super(subscription);
    }
}
