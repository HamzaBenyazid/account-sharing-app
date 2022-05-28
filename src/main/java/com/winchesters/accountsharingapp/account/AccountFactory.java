package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.exception.account.InvalidAccountProviderException;

public class AccountFactory {
    public static Account createAccount(AccountProvider provider){
        Account account;
        switch (provider){
            case FAKE -> account = new FakeAccount();
            case HBO -> account = new HBOAccount();
            case NETFLIX -> account = new NetflixAccount();
            case SPOTIFY -> account = new SpotifyAccount();
            default -> throw new InvalidAccountProviderException();
        }
        return account;
    }
}
