package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.dto.AccountResponseDto;
import com.winchesters.accountsharingapp.dto.CreateAccountDto;
import com.winchesters.accountsharingapp.exception.account.AccountNotFoundException;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionService;
import com.winchesters.accountsharingapp.user.User;
import com.winchesters.accountsharingapp.user.UserService;
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
    private final UserService userService;

    private final Logger Log = LoggerFactory.getLogger(AccountService.class);

    public List<Account> listUserAccounts(String username){
       return accountRepository.findAllByOwnerUsername(username);
    }

    public List<AccountResponseDto> listAccounts(){
        return EntityToDtoMapper.accountToAccountResponseDto(accountRepository.findAll());
    }


    public Account createAccount(CreateAccountDto dto){
        Account account = AccountFactory.createAccount(dto.provider());
        account.setSubscriptionType(dto.subscriptionType());
        account.setOwner(userService.getUser());
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
        accountRepository.save(account);
    }

}
