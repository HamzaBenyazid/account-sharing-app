package com.winchesters.accountsharingapp.subscription.hbo;

import java.util.List;

public class HBOPremiumSubscription extends HBOSubscription{

    public HBOPremiumSubscription() {
        super(
                8,
                2,
                2000,
                List.of("FACEBOOK","TWITTER","LINKEDIN","PINTEREST"),
                65
        );
    }
}
