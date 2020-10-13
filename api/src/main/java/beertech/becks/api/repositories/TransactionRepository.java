package beertech.becks.api.repositories;

import beertech.becks.api.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select sum(t.valueTransaction) from Transaction t where t.currentAccount.hashValue = :hashValue")
    Optional<BigDecimal> getSumValueBalanceByHash(@Param("hashValue") String hashValue);

}
