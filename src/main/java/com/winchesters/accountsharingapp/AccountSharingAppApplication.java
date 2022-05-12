package com.winchesters.accountsharingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AccountSharingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountSharingAppApplication.class, args);
    }

}
