package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.dto.AccountResponseDto;
import com.winchesters.accountsharingapp.dto.CreateAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping()
    public void createAccount(@RequestBody CreateAccountDto accountDto){
        accountService.createAccount(accountDto);
    }

    @DeleteMapping("{accountId}")
    public void deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
    }

    @PutMapping("{accountId}")
    public void updateCredentials(@RequestBody Credentials credentials,@PathVariable Long accountId){
        accountService.updateCredentials(accountId,credentials);
    }

    @GetMapping
    public List<AccountResponseDto> listAccounts(){
        return accountService.listAccounts();
    }
}
