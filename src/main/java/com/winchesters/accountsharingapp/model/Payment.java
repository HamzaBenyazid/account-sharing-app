package com.winchesters.accountsharingapp.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Payment {
    private Long id;
    private Double total;
    private Date paymentDate;
}
