package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.exception.account.InvalidAccountProviderException;

public class AccountFactory {
    public static Account createAccount(String provider){
        Account account = null;
        switch (AccountProvider.valueOf(provider)){
            case FAKE -> account = new FakeAccount();
            case HBO -> account = new HBOAccount();
            case NETFLIX -> account = new NetflixAccount();
            case SPOTIFY -> account = new SpotifyAccount();
            default -> throw new InvalidAccountProviderException();
        }
        return account;
    }
}
