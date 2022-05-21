package com.winchesters.accountsharingapp.exception.user;


import com.winchesters.accountsharingapp.exception.GeneralException;

public class InvalidEmailException extends GeneralException {


    public InvalidEmailException(String message) {
        super("InvalidUsernameException", message);
    }
}
