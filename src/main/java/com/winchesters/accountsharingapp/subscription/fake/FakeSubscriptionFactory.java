package com.winchesters.accountsharingapp.subscription.fake;

import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionFactory;

public class FakeSubscriptionFactory implements SubscriptionFactory {
    @Override
    public Subscription createSubscription(String type) {
        return new FakeSubscription();
    }
}
