package com.winchesters.accountsharingapp.model.account;

import com.winchesters.accountsharingapp.model.Credentials;
import com.winchesters.accountsharingapp.model.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Account {

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
