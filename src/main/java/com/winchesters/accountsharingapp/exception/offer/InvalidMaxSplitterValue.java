package com.winchesters.accountsharingapp.exception.offer;

import com.winchesters.accountsharingapp.exception.GeneralException;

public class InvalidMaxSplitterValue extends GeneralException {
    public InvalidMaxSplitterValue(String message) {
        super("MaxSplittersExceededException", message);
    }
}
