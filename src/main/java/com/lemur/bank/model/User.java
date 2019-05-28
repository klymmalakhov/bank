package com.lemur.bank.model;

import lombok.*;

import javax.persistence.*;
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

    @OneToOne
    private Account account;

    public User(String name, String password, String email, Account account) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.account = account;
    }
}
