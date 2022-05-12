package com.winchesters.accountsharingapp.payment;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;
    private Date paymentDate;
}
