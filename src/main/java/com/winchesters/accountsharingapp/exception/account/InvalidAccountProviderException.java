package com.winchesters.accountsharingapp.exception.account;

import com.winchesters.accountsharingapp.exception.GeneralException;

public class InvalidAccountProviderException extends GeneralException {
    public InvalidAccountProviderException() {
        super("InvalidAccountProviderException","invalid account provider");
    }
}
