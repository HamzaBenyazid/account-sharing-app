package com.winchesters.accountsharingapp.exception.offer;

import com.winchesters.accountsharingapp.exception.GeneralException;

public class OfferNotEmptyException extends GeneralException {
    public OfferNotEmptyException(String message) {
        super("OfferNotEmptyException", message);
    }
}
