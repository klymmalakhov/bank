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
    private String town;
    private String country;
    private BigDecimal amount;
    private Currency currency;

    public UserResponse(String password, String name, String email, String town, String country, BigDecimal amount, Currency currency) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.town = town;
        this.country = country;
        this.amount = amount;
        this.currency = currency;
    }
}
