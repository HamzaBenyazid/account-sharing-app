package com.winchesters.accountsharingapp.subscription;

import com.winchesters.accountsharingapp.account.AccountProvider;

public interface Subscription {
    Double getPrice();
    Integer getMaxUsers();
}
