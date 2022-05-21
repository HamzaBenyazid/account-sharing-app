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
        Account account = null;
        switch (AccountProvider.valueOf(dto.provider())){
            case FAKE -> account = new FakeAccount();
            case HBO -> account = new HBOAccount();
            case NETFLIX -> account = new NetflixAccount();
            case SPOTIFY -> account = new SpotifyAccount();
            default -> throw new InvalidAccountProviderException();
        }

        Subscription subscription = subscriptionService.createSubscription(account,dto.subscriptionType());
        account.setSubscription(subscription);

        return accountRepository.save(account);
    }


    public void deleteAccount(){

    }

}
