package com.winchesters.accountsharingapp.payment;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = PaymentController.class)
public class PaymentControllerAdvice {
}
