package com.winchesters.accountsharingapp.exception;

public class JwtTokenNotValidException extends RuntimeException {

    public JwtTokenNotValidException() {
        super("Invalid jwt token");
    }
}
