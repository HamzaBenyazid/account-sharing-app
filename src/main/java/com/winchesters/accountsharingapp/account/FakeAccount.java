package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.fake.FakeSubscriptionFactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;


//This class is just for dev
@DiscriminatorValue("FakeAccount")
@Entity
public class FakeAccount extends Account {
    public FakeAccount() {
        super(
                new FakeSubscriptionFactory(),
                FAKE
        );
    }
}
