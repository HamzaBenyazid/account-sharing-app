package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.AccountProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.security.Provider;

@Repository
public interface OfferRepository extends JpaRepository <Offer,Long> {
    Page<Offer> findByOffererId(Long offererId, Pageable page);
    Page<Offer> findByOffererUsername(String username, Pageable page);
    @Query("SELECT c FROM Offer c WHERE (:price is null or c.calculatedPrice = :price) and (:provider is null or c.account.provider = :provider)")
    Page<Offer> findByCalculatedPriceAndAccount_Provider(@Param("price") Double calculatedPrice, @Param("provider") AccountProvider provider, Pageable page);

}
