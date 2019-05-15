package com.lemur.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private String id;
    private String password;
    private String name;
    private String email;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Event event;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Account account;
}
