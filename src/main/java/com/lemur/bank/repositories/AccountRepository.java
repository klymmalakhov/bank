package com.lemur.bank.repositories;


import com.lemur.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

}