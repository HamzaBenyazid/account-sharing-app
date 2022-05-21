package com.winchesters.accountsharingapp.subscription.hbo;

import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public abstract class HBOSubscription implements Subscription {
    private Integer socialAccounts;
    private Integer users;
    private Integer scheduledPostsPerSocialAccount;
    private List<String> socialNetworks;
    private double monthlyPricing;



    @Override
    public Double getPrice() {
        return monthlyPricing;
    }

    @Override
    public Integer getMaxUsers() {
        return users;
    }
}
