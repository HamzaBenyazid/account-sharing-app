package com.winchesters.accountsharingapp.subscription;

import javax.persistence.Embeddable;


public interface Subscription {
    Double getPrice();
    Integer getMaxUsers();
}
