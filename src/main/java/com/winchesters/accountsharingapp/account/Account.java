package com.winchesters.accountsharingapp.account;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public abstract class Account {

    @Id
    @Column(name = "id_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Subscription subscription;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Credentials credentials;

    @OneToMany(mappedBy="account", fetch = FetchType.LAZY)
    private List<Offer> offers;

    public Account(Subscription subscription) {
        this.subscription = subscription;
    }

}
