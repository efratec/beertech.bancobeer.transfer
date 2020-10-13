package beertech.becks.api.repositories;

import beertech.becks.api.entities.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {


    @Query(value = "select max(c.idCurrentAccount) from CurrentAccount c")
    Long getLastIdCurrentAccount();

    CurrentAccount findByHashValue(String hashValue);

    @Query(value = "select c.hashValue from CurrentAccount c where c.hashValue = :hashValue")
    BigDecimal getBalanceCurrentAccount(@Param("hashValue") String hashValue);

}
