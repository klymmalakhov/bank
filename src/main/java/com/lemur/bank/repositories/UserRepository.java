package com.lemur.bank.repositories;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.Event;
import com.lemur.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.account FROM User u WHERE u.id = ?1")
    Collection<Account> findAllAccountsForUser(@Param("id") Long id);

}