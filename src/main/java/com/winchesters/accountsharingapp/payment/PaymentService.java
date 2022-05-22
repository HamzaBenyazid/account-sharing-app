package com.winchesters.accountsharingapp.payment;

import com.winchesters.accountsharingapp.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class PaymentService {
    private static final Logger Log = LoggerFactory.getLogger(PaymentService.class);
}
