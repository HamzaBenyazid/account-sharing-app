package com.winchesters.accountsharingapp.model.account;

import com.winchesters.accountsharingapp.model.subscription.FakeSubscription;


//This class is just for dev
public class FakeAccount extends Account {
    public FakeAccount() {
        super(new FakeSubscription());
    }
}
