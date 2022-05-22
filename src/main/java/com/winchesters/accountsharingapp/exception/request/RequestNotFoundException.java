package com.winchesters.accountsharingapp.exception.request;

import com.winchesters.accountsharingapp.exception.GeneralException;

public class RequestNotFoundException extends GeneralException {
    public RequestNotFoundException(Long id) {
        super("RequestNotFoundException", String.format("Request with id %s not found",id));
    }
}
