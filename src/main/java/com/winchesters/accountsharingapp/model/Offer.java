package com.winchesters.accountsharingapp.model;

import java.util.Date;
import java.util.List;

public class Offer {
    private Long id;
    private Date OfferDate;
    private Double calculatedPrice;
    private Integer maxUsers;
    private List<User> users;
}
