package com.winchesters.accountsharingapp.account;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.subscription.Subscription;
import com.winchesters.accountsharingapp.subscription.SubscriptionFactory;
import com.winchesters.accountsharingapp.subscription.hbo.HBOSubscriptionType;
import com.winchesters.accountsharingapp.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountProvider provider;

    @NotNull
    @NotEmpty
    private String subscriptionType;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Credentials credentials;

    @OneToMany(mappedBy="account", fetch = FetchType.LAZY)
    private List<Offer> offers;

    @ManyToOne
    private User owner;

    @Transient
    private SubscriptionFactory subscriptionFactory ;

    public Account(SubscriptionFactory subscriptionFactory,AccountProvider provider) {
        this.subscriptionFactory = subscriptionFactory;
        this.provider = provider;
    }

    public Subscription getSubscription(){
        return subscriptionFactory.createSubscription(this.subscriptionType);
    }

}
