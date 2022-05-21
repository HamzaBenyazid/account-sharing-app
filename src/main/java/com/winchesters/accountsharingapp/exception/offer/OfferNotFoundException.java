package com.winchesters.accountsharingapp.exception.offer;

import com.winchesters.accountsharingapp.exception.GeneralException;

public class OfferNotFoundException extends GeneralException {

    public OfferNotFoundException(String message) {
        super("OfferNotFoundException", message);
    }
}
