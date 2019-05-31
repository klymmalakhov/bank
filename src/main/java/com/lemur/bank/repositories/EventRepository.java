package com.lemur.bank.repositories;

import com.lemur.bank.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}