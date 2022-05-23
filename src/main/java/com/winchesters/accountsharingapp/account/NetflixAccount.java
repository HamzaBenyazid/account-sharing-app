package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.netflix.NetflixSubscriptionFactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;

@DiscriminatorValue("NetflixAccount")
@Entity
public class NetflixAccount extends Account{

    public NetflixAccount() {
        super(
                new NetflixSubscriptionFactory(),
                NETFLIX
        );
    }
}
