package com.winchesters.accountsharingapp.subscription.spotify;

import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionFactory;

public class SpotifySubscriptionFactory implements SubscriptionFactory {
    @Override
    public Subscription createSubscription(String type) {
        Subscription subscription = null;
        switch (SpotifySubscriptionType.valueOf(type)){
            case PREMIUM -> subscription = new SpotifyPremiumSubscription();
        }
        return subscription;
    }
}
