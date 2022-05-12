package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.Subscription;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public abstract class Account {

    @Id
    private Long id;
    @Embedded
    private Subscription subscription;

    @Embedded
    private Credentials credentials;

    public Account(Subscription subscription) {
        this.subscription = subscription;
    }
}
