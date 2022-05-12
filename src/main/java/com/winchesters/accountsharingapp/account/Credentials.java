package com.winchesters.accountsharingapp.account;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Credentials {
    private String username;
    private String password;
}
