package com.lemur.bank.repositories;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("SELECT u FROM Event u WHERE u.id = ?1")
    Collection<Event> findAllEventsWithAmount(@Param("id") Long id);

    @Query("SELECT u FROM Event u WHERE u.destination = ?1")
    Collection<Event> findAllEventsByAccount(@Param("destination") Long destination);

}