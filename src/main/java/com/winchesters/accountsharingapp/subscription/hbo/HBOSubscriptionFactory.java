package com.winchesters.accountsharingapp.subscription.hbo;

import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionFactory;

public class HBOSubscriptionFactory implements SubscriptionFactory {
    @Override
    public Subscription createSubscription(String type) {
        Subscription subscription = null;
        switch (HBOSubscriptionType.valueOf(type)){
            case PREMIUM -> subscription = new HBOPremiumSubscription();
            case SMALL_BUSINESS -> subscription = new HBOSmallBusinessSubscription();
        }
        return  subscription;
    }
}
