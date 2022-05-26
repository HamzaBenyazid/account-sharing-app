package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.hbo.HBOSubscriptionFactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;

@DiscriminatorValue("HBOAccount")
@Entity
public class HBOAccount extends Account{
    public HBOAccount() {
        super(
                new HBOSubscriptionFactory(),
                HBO
        );
    }
}
