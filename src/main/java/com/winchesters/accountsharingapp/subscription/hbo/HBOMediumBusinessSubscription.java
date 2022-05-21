package com.winchesters.accountsharingapp.subscription.hbo;

import java.util.List;

public class HBOMediumBusinessSubscription extends HBOSubscription{
    public HBOMediumBusinessSubscription(Integer socialAccounts, Integer users, Integer scheduledPostsPerSocialAccount, List<String> socialNetworks, double monthlyPricing) {
        super(
                50,
                11,
                2000,
                List.of("FACEBOOK","TWITTER","LINKEDIN","PINTEREST"),
                199
        );
    }
}
