package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.dto.AccountResponseDto;
import com.winchesters.accountsharingapp.dto.CreateAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping()
    public ResponseEntity<Long> createAccount(@RequestBody CreateAccountDto accountDto){
        return new ResponseEntity<>(
                accountService.createAccount(accountDto).getId(),
                HttpStatus.CREATED
        );
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
