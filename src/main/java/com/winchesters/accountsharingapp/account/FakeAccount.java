package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.FakeSubscription;


//This class is just for dev
public class FakeAccount extends Account {
    public FakeAccount() {
        super(new FakeSubscription());
    }
}
