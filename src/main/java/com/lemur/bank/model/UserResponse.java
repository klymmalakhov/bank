package com.lemur.bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@NoArgsConstructor
@Entity
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;
    private String name;
    private String email;
    private BigDecimal amount;
    private Currency currency;

    public UserResponse(String password, String name, String email, BigDecimal amount, Currency currency) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.amount = amount;
        this.currency = currency;
    }
}
