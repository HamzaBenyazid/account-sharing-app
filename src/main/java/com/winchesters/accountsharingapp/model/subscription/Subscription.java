package com.winchesters.accountsharingapp.model.subscription;

import javax.persistence.Embeddable;


public interface Subscription {
    Double getPrice();
    Integer getMaxUsers();
}
