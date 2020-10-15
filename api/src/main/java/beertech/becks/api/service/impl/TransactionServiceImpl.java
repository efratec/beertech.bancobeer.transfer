package beertech.becks.api.service.impl;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.CurrentAccountService;
import beertech.becks.api.service.TransactionService;
import beertech.becks.api.share.DTO.CurrentAccountDTO;
import beertech.becks.api.share.DTO.CurrentAccountTransferDTO;
import beertech.becks.api.share.DTO.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static beertech.becks.api.entities.builder.TransactionBuilder.aTransaction;
import static beertech.becks.api.model.enums.TypeOperation.*;
import static java.math.BigDecimal.ZERO;
import static java.time.ZonedDateTime.now;
import static java.util.Optional.ofNullable;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private CurrentAccountService currentAccountService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, CurrentAccountService currentAccountService) {
        this.transactionRepository = transactionRepository;
        this.currentAccountService = currentAccountService;
    }

    @Override
    public Transaction createTransaction(TransactionRequestDTO transactionRequestDTO) {
        return executeTransaction(transactionRequestDTO);
    }

    private Transaction executeTransaction(TransactionRequestDTO transactionRequestDTO) {
        return saveTransaction(getTransactionForSave(transactionRequestDTO));
    }

    private Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    private Transaction getTransactionForSave(TransactionRequestDTO dto) {
        return aTransaction()
                .withOperation(getTypeOperationByDescription(dto.getOperation()))
                .withValueTransaction(getValueTransactionByOperation(dto))
                .withCurrentAccount(updateCurrentsAccountsBalance(dto))
                .withDestinationHashValue(dto.getDestinationAccount())
                .withDateTime(now())
                .builder();
    }

    private CurrentAccount updateCurrentsAccountsBalance(TransactionRequestDTO dto) {
        return isTransfer(dto.getOperation()) ? doCurrentAccountTransfer(dto) : updateCurrentAccountBalance(dto);
    }

    private CurrentAccount doCurrentAccountTransfer(TransactionRequestDTO transactionRequestDTO) {
        CurrentAccountTransferDTO currentAccountTransferDTO =
                CurrentAccountTransferDTO
                        .builder()
                        .currentAccountIndentify(transactionRequestDTO.getOriginAccount())
                        .destinationCurrentAccount(transactionRequestDTO.getDestinationAccount())
                        .balance(transactionRequestDTO.getValue())
                        .build();
        return currentAccountService.doCurrentAccountTransferTranscation(currentAccountTransferDTO);
    }

    private CurrentAccount updateCurrentAccountBalance(TransactionRequestDTO dto) {
        BigDecimal valueBalance = getSumValueBalanceByHash(dto.getOriginAccount(), dto.getOperation());
        CurrentAccountDTO currentAccountDTO = CurrentAccountDTO
                .builder()
                .updatedBalance(isWITHDRAW(dto.getOperation()) ? valueBalance.subtract(dto.getValue()) : valueBalance.add(dto.getValue()))
                .hashValue(dto.getOriginAccount())
                .build();
        return currentAccountService.updateCurrentAccountTranscation(currentAccountDTO);
    }

    private BigDecimal getSumValueBalanceByHash(String hashValue, String operation) {
        Optional<BigDecimal> sumValueBalance = transactionRepository.getSumValueBalanceByHash(hashValue);
        return sumValueBalance.isPresent() ? sumValueBalance.get() : ZERO;
    }

    private static BigDecimal getValueTransactionByOperation(TransactionRequestDTO dto) {
        return isWITHDRAW(dto.getOperation()) ? dto.getValue().negate() : dto.getValue();
    }

}
