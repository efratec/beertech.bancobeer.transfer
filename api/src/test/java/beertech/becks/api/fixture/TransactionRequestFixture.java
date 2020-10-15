package beertech.becks.api.fixture;

import beertech.becks.api.share.DTO.TransactionRequestDTO;

import static beertech.becks.api.constants.ContantsTests.Hash.HASH_c81e728d9d4c2f636f067f89cc14862c;
import static beertech.becks.api.constants.ContantsTests.Hash.HASH_eccbc87e4b5ce2fe28308fd9f2a7baf3;
import static beertech.becks.api.constants.ContantsTests.Values.VALUE_100;
import static beertech.becks.api.constants.ContantsTests.Values.VALUE_30;
import static beertech.becks.api.model.enums.TypeOperation.*;
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

    public static TransactionRequestDTO aTransferTransactionRequest() {
        return builder()
                .operation(TRANSFER.getDescription())
                .originAccount(HASH_c81e728d9d4c2f636f067f89cc14862c)
                .destinationAccount(HASH_eccbc87e4b5ce2fe28308fd9f2a7baf3)
                .value(VALUE_30)
                .build();
    }

}
