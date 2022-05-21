package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.hbo.HBOSubscriptionFactory;

public class HBOAccount extends Account{
    public HBOAccount() {
        super(
                new HBOSubscriptionFactory(),
                AccountProvider.HBO
        );
    }
}
