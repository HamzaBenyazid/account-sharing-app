package com.winchesters.accountsharingapp.exception.user;


import com.winchesters.accountsharingapp.exception.GeneralException;

public class InvalidUsernameException extends GeneralException {
    public InvalidUsernameException(String msg) {
        super("InvalidUsernameException",msg);
    }
}
