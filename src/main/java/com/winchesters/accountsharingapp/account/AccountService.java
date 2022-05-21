package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.account.dto.CreateAccountDto;
import com.winchesters.accountsharingapp.exception.InvalidAccountProviderException;
import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final SubscriptionService subscriptionService;

    private final Logger Log = LoggerFactory.getLogger(AccountService.class);

    public List<Account> listUserAccounts(String username){
       return accountRepository.findAllByOwnerUsername(username);
    }

    public Account createAccount(CreateAccountDto dto){
        Account account = AccountFactory.createAccount(dto.provider());
        Subscription subscription = subscriptionService.createSubscription(account,dto.subscriptionType());
        account.setSubscription(subscription);

        return accountRepository.save(account);
    }


    public void deleteAccount(){

    }

}
