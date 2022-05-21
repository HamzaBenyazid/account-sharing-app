package com.winchesters.accountsharingapp.subscription.fake;


import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.Getter;

//This class is just for dev
@Getter
public class FakeSubscription implements Subscription {

    String description = "this is a fake subscription";

    @Override
    public Double getPrice() {
        return 20D;
    }

    @Override
    public Integer getMaxUsers() {
        return 10;
    }
}
