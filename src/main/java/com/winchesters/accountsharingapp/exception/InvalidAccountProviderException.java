package com.winchesters.accountsharingapp.exception;

public class InvalidAccountProviderException extends RuntimeException{
    public InvalidAccountProviderException() {
        super("invalid account provider");
    }
}
