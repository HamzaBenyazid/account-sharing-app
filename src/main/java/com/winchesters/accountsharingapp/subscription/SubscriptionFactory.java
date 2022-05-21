package com.winchesters.accountsharingapp.subscription;

public interface SubscriptionFactory {
    Subscription createSubscription(String type);
}
