package it.si2001.lucapuzzoni.bank_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.si2001.lucapuzzoni.bank_test.enitity.Transaction;

/**
 * @author Luca Puzzoni
 * Repository per le transazioni
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{}