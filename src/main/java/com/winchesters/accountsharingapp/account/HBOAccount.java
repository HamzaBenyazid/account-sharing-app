package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.hbo.HBOSubscriptionFactory;

import static com.winchesters.accountsharingapp.account.AccountProvider.*;

public class HBOAccount extends Account{
    public HBOAccount() {
        super(
                new HBOSubscriptionFactory(),
                HBO
        );
    }
}
