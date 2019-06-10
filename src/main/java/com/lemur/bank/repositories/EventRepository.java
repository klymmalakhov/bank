package com.lemur.bank.repositories;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("SELECT u FROM Event u WHERE u.id = ?1")
    Collection<Event> findAllEventsWithAmount(@Param("id") Long id);

    @Query("SELECT u FROM Event u WHERE u.destination = :#{#account}")
    Collection<Event> findAllEventsByAccount(@Param("account") Account account);

    @Query("SELECT u FROM Event u WHERE u.destination = :#{#account} and u.date > :fromDate")
    Collection<Event> findAllEventsByAccountAndFromDate(@Param("account") Account account, @Param("fromDate") Instant fromDate);

    @Query("SELECT u FROM Event u WHERE u.destination = :#{#account} and u.date < :tillDate")
    Collection<Event> findAllEventsByAccountAndTillDate(@Param("account") Account account, @Param("tillDate") Instant tillDate);

    @Query("SELECT u FROM Event u WHERE u.destination = :#{#account} and u.date > :fromDate and u.date < :tillDate")
    Collection<Event> findAllEventsByAccountAndTillDateFromDate(@Param("account") Account account, @Param("fromDate") Instant fromDate, @Param("tillDate") Instant tillDate);
}