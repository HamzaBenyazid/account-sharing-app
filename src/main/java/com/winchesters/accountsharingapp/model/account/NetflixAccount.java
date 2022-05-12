package com.winchesters.accountsharingapp.model.account;

import com.winchesters.accountsharingapp.model.subscription.netflix.NetflixSubscription;

public class NetflixAccount extends Account{

    public NetflixAccount(NetflixSubscription subscription) {
        super(subscription);
    }
}
