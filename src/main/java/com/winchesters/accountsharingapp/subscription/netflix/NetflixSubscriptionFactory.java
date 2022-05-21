package com.winchesters.accountsharingapp.subscription.netflix;

import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionFactory;

public class NetflixSubscriptionFactory implements SubscriptionFactory {
    @Override
    public Subscription createSubscription(String type) {
        NetflixSubscriptionType netflixSubscriptionType = NetflixSubscriptionType.valueOf(type);
        Subscription subscription = null;
        switch (netflixSubscriptionType){
            case PREMIUM -> subscription = new NetflixPremiumSubscription();
            case STANDARD -> subscription = new NetflixStandardSubscription();
        }
        return subscription;
    }
}
