package beertech.becks.api.service.transaction;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.model.AbstractTests;
import beertech.becks.api.service.impl.TransactionServiceImpl;
import beertech.becks.api.share.DTO.TransactionRequestDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static beertech.becks.api.fixture.CurrentAccountFixture.aCurrentAccountForTests;
import static beertech.becks.api.fixture.TransactionFixture.aDepositTransaction;
import static beertech.becks.api.fixture.TransactionFixture.aWithdrawTransaction;
import static beertech.becks.api.fixture.TransactionRequestFixture.aDepositTransactionRequest;
import static beertech.becks.api.fixture.TransactionRequestFixture.aWithdrawTransactionRequest;
import static java.math.BigDecimal.ZERO;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransactionServiceTest extends AbstractTests {

    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            whenTransactionsInTheCurrenAccount.class
    })
    public static class AllTests {
    }

    @Before
    public void setUp() {
        initMocks(this);
    }

    public static class whenTransactionsInTheCurrenAccount extends TransactionServiceTest {

        private Transaction transactionExpected;
        private TransactionRequestDTO transactionRequestDTO;
        private CurrentAccount currentAccount;

        @Before
        public void buildContext() {
            currentAccount = aCurrentAccountForTests();
        }

        @Test
        public void givenWithdrawInTheCurrentAccountWhenCreateTransactionThenShouldReturnSuccess() {

            transactionExpected = aWithdrawTransaction();
            transactionRequestDTO = aWithdrawTransactionRequest();

            transactionService = new TransactionServiceImpl(transactionRepositoryMock, currentAccountServiceMock);

            allowReturns();

            Transaction getTransaction = transactionService.createTransaction(transactionRequestDTO);

            assertNotNull(transactionExpected.getDateTime());
            assertNotNull(transactionExpected.getDateTime());
            assertEquals(transactionRequestDTO.getOperation(), transactionExpected.getTypeOperation().getDescription());
            assertEquals(transactionRequestDTO.getValue().negate(), transactionExpected.getValueTransaction());
        }

        @Test
        public void givenDepositInTheCurrentAccountWhenCreateTransactionThenShouldReturnSuccess() {

            transactionExpected = aDepositTransaction();
            transactionRequestDTO = aDepositTransactionRequest();

            transactionService = new TransactionServiceImpl(transactionRepositoryMock, currentAccountServiceMock);

            allowReturns();
            Transaction transactionGetted = transactionService.createTransaction(transactionRequestDTO);

            assertNotNull(transactionGetted.getDateTime());
            assertNotNull(transactionGetted.getDateTime());
            assertEquals(transactionGetted.getTypeOperation().getDescription(), transactionExpected.getTypeOperation().getDescription());
            assertEquals(transactionGetted.getValueTransaction(), transactionExpected.getValueTransaction());
        }

        private void allowReturns() {
            when(currentAccountServiceMock.updateCurrentAccountTranscation(any())).thenReturn(currentAccount);
            when(transactionRepositoryMock.getSumValueBalanceByHash(any())).thenReturn(of(ZERO));
            when(transactionRepositoryMock.save(any(Transaction.class))).thenReturn(transactionExpected);
        }

       /* @Test
        public void givenTransferInTheCurrentAccountWhenCreateTransactionThenShouldReturnSuccess() {

            transaction = aDepositTransaction();
            transactionRequestDTO = aDepositTransactionRequest();

            transactionService = new TransactionServiceImpl(transactionRepositoryMock, currentAccountServiceMock);

            allowReturns();

            Transaction transaction = transactionService.createTransaction(transactionRequestDTO);

            assertNotNull(transaction.getDateTime());
            assertNotNull(transaction.getDateTime());
            assertEquals(transactionRequestDTO.getOperation(), transaction.getTypeOperation().getDescription());
            assertEquals(transactionRequestDTO.getValue(), transaction.getValueTransaction());
        }*/
    }

}
