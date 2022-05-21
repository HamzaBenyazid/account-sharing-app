package com.winchesters.accountsharingapp.subscription.hbo;

import java.util.List;

public class HBOSmallBusinessSubscription extends HBOSubscription{
    public HBOSmallBusinessSubscription() {
        super(
                25,
                6,
                2000,
                List.of("FACEBOOK","TWITTER","LINKEDIN","PINTEREST"),
                99
        );
    }
}
