package com.winchesters.accountsharingapp.exception.request;

import com.winchesters.accountsharingapp.exception.GeneralException;

public class InvalidRequesterException extends GeneralException {
    public InvalidRequesterException(String message) {
        super("InvalidRequesterException", message);
    }
}
