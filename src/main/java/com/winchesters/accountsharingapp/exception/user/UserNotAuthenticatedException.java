package com.winchesters.accountsharingapp.exception.user;


import com.winchesters.accountsharingapp.exception.GeneralException;

public class UserNotAuthenticatedException extends GeneralException {
    public UserNotAuthenticatedException() {
        super("UserNotAuthenticatedException", "user is not authenticated");
    }
}
