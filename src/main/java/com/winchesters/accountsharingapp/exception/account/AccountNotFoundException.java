package com.winchesters.accountsharingapp.exception.account;

import com.winchesters.accountsharingapp.exception.GeneralException;

public class AccountNotFoundException extends GeneralException {
    public AccountNotFoundException(Long id) {
        super("AccountNotFoundException", String.format("account with id %s not found",id));
    }
}
