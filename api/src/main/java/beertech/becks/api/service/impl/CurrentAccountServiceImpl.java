package beertech.becks.api.service.impl;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.repositories.CurrentAccountRepository;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.CurrentAccountService;
import beertech.becks.api.share.DTO.BalanceDTO;
import beertech.becks.api.share.DTO.CurrentAccountDTO;
import beertech.becks.api.share.DTO.CurrentAccountTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

import static beertech.becks.api.constants.Constants.Hash.HASH_MD5;
import static beertech.becks.api.constants.Constants.NUMBERS.NUMBER_1L;
import static beertech.becks.api.entities.builder.CurrentAccountBuilder.aCurrentAccount;
import static beertech.becks.api.entities.builder.CurrentAccountBuilder.getHashInitialDefaut;
import static beertech.becks.api.entities.builder.TransactionBuilder.aTransaction;
import static beertech.becks.api.model.enums.TypeOperation.*;
import static beertech.becks.api.utils.HashGenerator.generatorHash;
import static java.time.ZonedDateTime.now;

@Service
@Transactional
public class CurrentAccountServiceImpl implements CurrentAccountService {

    private CurrentAccountRepository currentAccountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public CurrentAccountServiceImpl(CurrentAccountRepository currentAccountRepository, TransactionRepository transactionRepository) {
        this.currentAccountRepository = currentAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public CurrentAccount createCurrentAccount() {
        Long lastCurrentAccount = findLastRegisteredCurrentAccount();
        return saveCurrentAccount(createCurrentAccount(lastCurrentAccount));
    }

    @Override
    public CurrentAccount saveCurrentAccount(CurrentAccount currentAccount) {
        return currentAccountRepository.save(currentAccount);
    }

    @Override
    public CurrentAccount updateCurrentAccountTranscation(CurrentAccountDTO currentAccountDTO) {
        CurrentAccount currentAccount = findCurrentAccountByHash(currentAccountDTO.getHashValue());
        currentAccount.setAccountBalance(currentAccountDTO.getUpdatedBalance());
        return saveCurrentAccount(currentAccount);
    }

    @Override
    public CurrentAccount doCurrentAccountTransferTranscation(CurrentAccountTransferDTO currentAccountTransferDTO) {

        CurrentAccount currentAccountOrigin = findCurrentAccountByHash(currentAccountTransferDTO.getCurrentAccountIndentify());
        CurrentAccount currentAccountDestiny = findCurrentAccountByHash(currentAccountTransferDTO.getDestinationCurrentAccount());

        updateBalanceAccountAOriginAndInsertTransaction(currentAccountOrigin, currentAccountTransferDTO);
        updateBalanceAccountDestinyAndInsertTransaction(currentAccountDestiny, currentAccountTransferDTO);

        return currentAccountOrigin;
    }

    private void updateBalanceAccountAOriginAndInsertTransaction(CurrentAccount currentAccountOrigin, CurrentAccountTransferDTO dto) {
        Optional<BigDecimal> previosBalance = transactionRepository.getSumValueBalanceByHash(currentAccountOrigin.getHashValue());
        currentAccountOrigin.setAccountBalance(previosBalance.isPresent() ? previosBalance.get().subtract(dto.getBalance()) : dto.getBalance());
        saveCurrentAccount(currentAccountOrigin);
        insertTransaction(currentAccountOrigin, currentAccountOrigin.getHashValue(), WITHDRAW.getDescription());
    }

    private void updateBalanceAccountDestinyAndInsertTransaction(CurrentAccount currentAccountDestiny, CurrentAccountTransferDTO dto) {
        Optional<BigDecimal> previosBalance = transactionRepository.getSumValueBalanceByHash(currentAccountDestiny.getHashValue());
        currentAccountDestiny.setAccountBalance(previosBalance.isPresent() ? previosBalance.get().add(dto.getBalance()) : dto.getBalance());
        saveCurrentAccount(currentAccountDestiny);
        insertTransaction(currentAccountDestiny, currentAccountDestiny.getHashValue(), DEPOSIT.getDescription());
    }

    private void insertTransaction(CurrentAccount currentAccountOrigin, String destinationAccount, String operation) {
        Transaction transactionInsert = aTransaction()
                .withOperation(getTypeOperationByDescription(operation))
                .withValueTransaction(currentAccountOrigin.getAccountBalance().negate())
                .withCurrentAccount(currentAccountOrigin)
                .withDateTime(now())
                .builder();
        transactionRepository.save(transactionInsert);
    }

    @Override
    public BalanceDTO getAccountBalanceByHash(String hashValue) {
        return new BalanceDTO(getByAccountBalanceByHashAccount(hashValue));
    }

    private BigDecimal getByAccountBalanceByHashAccount(String hashValue) {
        return currentAccountRepository.getBalanceCurrentAccount(hashValue);
    }

    @Override
    public CurrentAccount findCurrentAccountByHash(String hashCurrentAccount) {
        return currentAccountRepository.findByHashValue(hashCurrentAccount);
    }

    private CurrentAccount createCurrentAccount(Long id) {
        return buildCurrentAccountWithHash(id);
    }

    private CurrentAccount buildCurrentAccountWithHash(Long id) {
        if (id != null) {
            Long newId = id + NUMBER_1L;
            return aCurrentAccount()
                    .withHashAccount(getHash(newId))
                    .withAccountBalance(BigDecimal.ZERO)
                    .builder();
        }
        return getHashInitialDefaut();
    }

    private String getHash(Long id) {
        return generatorHash(getStringHash(id), HASH_MD5);
    }

    private String getStringHash(Long newIdCurrentBuilder) {
        return newIdCurrentBuilder.toString();
    }

    private Long findLastRegisteredCurrentAccount() {
        return currentAccountRepository.getLastIdCurrentAccount();
    }

}
