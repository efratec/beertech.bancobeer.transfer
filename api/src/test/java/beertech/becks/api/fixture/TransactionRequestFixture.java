package beertech.becks.api.fixture;

import beertech.becks.api.share.DTO.TransactionRequestDTO;

import static beertech.becks.api.constants.ContantsTests.Hash.HASH_c81e728d9d4c2f636f067f89cc14862c;
import static beertech.becks.api.constants.ContantsTests.Values.VALUE_100;
import static beertech.becks.api.model.enums.TypeOperation.DEPOSIT;
import static beertech.becks.api.model.enums.TypeOperation.WITHDRAW;
import static beertech.becks.api.share.DTO.TransactionRequestDTO.builder;

public class TransactionRequestFixture {

    public static TransactionRequestDTO aWithdrawTransactionRequest() {
        return builder()
                .operation(WITHDRAW.getDescription())
                .originAccount(HASH_c81e728d9d4c2f636f067f89cc14862c)
                .value(VALUE_100)
                .build();
    }

    public static TransactionRequestDTO aDepositTransactionRequest() {
        return builder()
                .operation(DEPOSIT.getDescription())
                .originAccount(HASH_c81e728d9d4c2f636f067f89cc14862c)
                .value(VALUE_100)
                .build();
    }


}
