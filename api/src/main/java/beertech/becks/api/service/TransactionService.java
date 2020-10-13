package beertech.becks.api.service;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.share.DTO.TransactionRequestDTO;

public interface TransactionService {

    Transaction createTransaction(TransactionRequestDTO transactionTO);

    //BalanceDTO getBalance();

}
