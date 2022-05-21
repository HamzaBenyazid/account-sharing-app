package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.dto.CreateAccountDto;
import com.winchesters.accountsharingapp.exception.account.AccountNotFoundException;
import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
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


    public Account findAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException(id));
    }
    public void deleteAccount(Long id){
        Account account =  findAccountById(id);
        accountRepository.delete(account);
    }
    public void updateCredentials(Long accountId,Credentials credentials){
        Account account = findAccountById(accountId);
        account.setCredentials(credentials);
    }


}
