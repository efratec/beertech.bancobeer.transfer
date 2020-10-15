package beertech.becks.api.service.currentAccount;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.model.AbstractTests;
import beertech.becks.api.service.impl.CurrentAccountServiceImpl;
import beertech.becks.api.share.DTO.BalanceDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static beertech.becks.api.constants.Constants.NUMBERS.VALUE_100;
import static beertech.becks.api.constants.ContantsTests.Hash.HASH_c81e728d9d4c2f636f067f89cc14862c;
import static beertech.becks.api.fixture.CurrentAccountFixture.aCurrentAccountForTests;
import static java.math.BigDecimal.ZERO;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CurrentAccountServiceTests extends AbstractTests {

    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            WhenCreateCurrentAccount.class
    })
    public static class AllTests {
    }

    @Before
    public void setUp() {
        initMocks(this);
    }

    public static class WhenCreateCurrentAccount extends CurrentAccountServiceTests {

        private CurrentAccount currentAccountExpected;
        private BalanceDTO balanceExpected = new BalanceDTO(VALUE_100);

        @Before
        public void buildContext() {
            currentAccountExpected = aCurrentAccountForTests();
            currentAccountServiceMock = new CurrentAccountServiceImpl(currentAccountRepository, transactionRepositoryMock);
        }

        @Test
        public void givenCreateACurrentAccountThenShouldReturnSucess() {

            when(currentAccountRepository.getLastIdCurrentAccount()).thenReturn(null);
            when(currentAccountRepository.save(any(CurrentAccount.class))).thenReturn(currentAccountExpected);

            CurrentAccount currentAccountGetted = currentAccountServiceMock.createCurrentAccount();

            assertNotNull(currentAccountGetted);
            assertEquals(currentAccountGetted.getAccountBalance(), currentAccountExpected.getAccountBalance());
            assertEquals(currentAccountGetted.getHashValue(), currentAccountExpected.getHashValue());
            assertEquals(currentAccountGetted.getIdCurrentAccount(), currentAccountExpected.getIdCurrentAccount());
        }

        @Test
        public void givenBalanceCurrentAccountThenShouldReturnSucess() {

            when(currentAccountRepository.getBalanceCurrentAccount(HASH_c81e728d9d4c2f636f067f89cc14862c)).thenReturn(ZERO);

            BalanceDTO balanceGetted = currentAccountServiceMock.getAccountBalanceByHash(HASH_c81e728d9d4c2f636f067f89cc14862c);

            assertNotNull(balanceGetted);
            assertEquals(balanceGetted.getBalance(), currentAccountExpected.getAccountBalance());
        }

    }

}
