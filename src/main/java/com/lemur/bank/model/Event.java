package com.lemur.bank.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Instant date;
    private BigDecimal amount;
    private Currency currency;
    private String description;

    //private Account source;

    //private Account destination;\

    public Event(Instant date, BigDecimal amount, Currency currency, String description) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }
}
