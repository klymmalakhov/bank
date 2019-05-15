package com.lemur.bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_group")
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private Currency currency;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;
}
