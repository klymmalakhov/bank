package com.lemur.bank.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;
    private String name;
    private String email;
    private String town;
    private String country;

    @OneToOne
    private Account account;

    public User(String password, String name, String email, String town, String country, Account account) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.town = town;
        this.country = country;
        this.account = account;
    }

    //??? Как заставить контролер брать этот конструктор для создание пользователя по АПИ
    public User(String password, String name, String email, String town, String country, BigDecimal amount, Currency currency) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.town = town;
        this.country = country;
        this.account = new Account(amount, currency);
    }
}
