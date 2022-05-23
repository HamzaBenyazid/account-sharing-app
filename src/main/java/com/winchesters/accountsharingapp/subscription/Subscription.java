package com.winchesters.accountsharingapp.subscription;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.Type;


public interface Subscription {
    Double getPrice();
    Integer getMaxUsers();
}
