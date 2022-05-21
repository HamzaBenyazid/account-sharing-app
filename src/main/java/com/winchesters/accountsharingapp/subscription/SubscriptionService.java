package com.winchesters.accountsharingapp.subscription;

import com.winchesters.accountsharingapp.account.Account;
import com.winchesters.accountsharingapp.account.dto.CreateAccountDto;
import com.winchesters.accountsharingapp.subscription.fake.FakeSubscription;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final Logger Log = LoggerFactory.getLogger(SubscriptionService.class);
    private final SubscriptionFactory subscriptionFactory;

    public final Subscription createSubscription(Account account,String type){
        return account.getSubscriptionFactory().createSubscription(type);
    }
}
