package beertech.becks.api.fixture;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.model.enums.TypeOperation;

import java.math.BigDecimal;

import static beertech.becks.api.constants.Constants.NUMBERS.NUMBER_1L;
import static beertech.becks.api.constants.ContantsTests.Hash.HASH_eccbc87e4b5ce2fe28308fd9f2a7baf3;
import static beertech.becks.api.entities.builder.TransactionBuilder.aTransaction;
import static beertech.becks.api.fixture.CurrentAccountFixture.aCurrentAccountForTests;
import static beertech.becks.api.model.enums.TypeOperation.*;
import static beertech.becks.api.utils.DateUtil.stringInZonedDateTime;

public class TransactionFixture {

    public static Transaction aWithdrawTransaction() {
        return aTransaction()
                .withCurrentAccount(aCurrentAccountForTests())
                .withDateTime(stringInZonedDateTime("10/10/2020"))
                .withOperation(WITHDRAW)
                .withValueTransaction(new BigDecimal("-100"))
                .builder();
    }

    public static Transaction aDepositTransaction() {
        return aTransaction()
                .withId(NUMBER_1L)
                .withCurrentAccount(aCurrentAccountForTests())
                .withDateTime(stringInZonedDateTime("10/10/2020"))
                .withOperation(DEPOSIT)
                .withValueTransaction(new BigDecimal("100"))
                .builder();
    }

    public static Transaction aTransferTransaction() {
        return aTransaction()
                .withCurrentAccount(aCurrentAccountForTests())
                .withDestinationHashValue(HASH_eccbc87e4b5ce2fe28308fd9f2a7baf3)
                .withDateTime(stringInZonedDateTime("10/10/2020"))
                .withOperation(TRANSFER)
                .withValueTransaction(new BigDecimal("30"))
                .builder();
    }

}
