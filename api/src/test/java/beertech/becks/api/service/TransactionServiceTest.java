package beertech.becks.api.service;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.model.enums.TypeOperation;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.impl.TransactionServiceImpl;
import beertech.becks.api.share.DTO.TransactionRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionRepository transactionRepository;

    private TransactionService service;

    private TransactionRequestDTO transactionTO;

    private Transaction transaction;

    private BigDecimal transactionValue;

    private CurrentAccountService currentAccountService;

    @Test
    public void given_withdraw_when_createTransaction_then_should_return_success() {

        transactionRepository = mock(TransactionRepository.class);

        //GIVEN
        service = new TransactionServiceImpl(transactionRepository, currentAccountService);
        transactionValue = new BigDecimal(100);

        transactionTO = new TransactionRequestDTO();
        transactionTO.setOperation("S");
        transactionTO.setValue(transactionValue);

        transaction = new Transaction();
        transaction.setValueTransaction(transactionTO.getValue().negate());
        transaction.setTypeOperation(TypeOperation.WITHDRAW);
        transaction.setDateTime(ZonedDateTime.now());

        //WHEN
        service.createTransaction(transactionTO);

        //THEN
        Assertions.assertNotNull(transaction.getDateTime());
        Assertions.assertEquals(transactionTO.getOperation(), transaction.getTypeOperation().getDescription());
        Assertions.assertEquals(transactionTO.getValue().negate(), transaction.getValueTransaction());
    }

    @Test
    public void given_deposit_when_createTransaction_then_should_return_success() {

        //GIVEN
        transactionRepository = mock(TransactionRepository.class);

        service = new TransactionServiceImpl(transactionRepository, currentAccountService);
        transactionValue = new BigDecimal(500);

        transactionTO = new TransactionRequestDTO();
        transactionTO.setOperation("D");
        transactionTO.setValue(transactionValue);

        transaction = new Transaction();
        transaction.setValueTransaction(transactionTO.getValue());
        transaction.setTypeOperation(TypeOperation.DEPOSIT);
        transaction.setDateTime(ZonedDateTime.now());

        //WHEN
        service.createTransaction(transactionTO);

        //THEN
        Assertions.assertNotNull(transaction.getDateTime());
        Assertions.assertEquals(transactionTO.getOperation(), transaction.getTypeOperation().getDescription());
        Assertions.assertEquals(transactionTO.getValue(), transaction.getValueTransaction());
    }
}
