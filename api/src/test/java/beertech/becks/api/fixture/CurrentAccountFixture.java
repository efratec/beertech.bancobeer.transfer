package beertech.becks.api.fixture;

import beertech.becks.api.entities.CurrentAccount;

import java.math.BigDecimal;

import static beertech.becks.api.constants.Constants.NUMBERS.NUMBER_1L;
import static beertech.becks.api.constants.ContantsTests.Hash.HASH_c81e728d9d4c2f636f067f89cc14862c;
import static beertech.becks.api.entities.builder.CurrentAccountBuilder.aCurrentAccount;

public class CurrentAccountFixture {

    public static CurrentAccount aCurrentAccountForTests() {
        return aCurrentAccount()
                .withIdCurrentAccount(NUMBER_1L)
                .withAccountBalance(new BigDecimal("0"))
                .withHashAccount(HASH_c81e728d9d4c2f636f067f89cc14862c)
                .builder();
    }

    public static CurrentAccount aCurrentAccountForTransfer() {
        return aCurrentAccount()
                .withIdCurrentAccount(NUMBER_1L)
                .withAccountBalance(new BigDecimal("100"))
                .withHashAccount(HASH_c81e728d9d4c2f636f067f89cc14862c)
                .builder();
    }

}
