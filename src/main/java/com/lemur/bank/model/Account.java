package com.lemur.bank.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;
    private Currency currency;

    public Account(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
