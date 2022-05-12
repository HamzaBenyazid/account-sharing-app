package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Subscription subscription;

    @Embedded
    private Credentials credentials;

    public Account(Subscription subscription) {
        this.subscription = subscription;
    }

}
