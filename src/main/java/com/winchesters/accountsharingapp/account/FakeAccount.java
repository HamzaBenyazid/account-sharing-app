package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.fake.FakeSubscriptionFactory;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;


//This class is just for dev
public class FakeAccount extends Account {
    public FakeAccount() {
        super(
                new FakeSubscriptionFactory(),
                FAKE
        );
    }
}
