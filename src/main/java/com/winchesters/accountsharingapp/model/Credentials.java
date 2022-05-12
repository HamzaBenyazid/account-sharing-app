package com.winchesters.accountsharingapp.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Credentials {
    private String username;
    private String password;
}
