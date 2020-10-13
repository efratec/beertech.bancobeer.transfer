package beertech.becks.api.service.impl;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.repositories.CurrentAccountRepository;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.CurrentAccountService;
import beertech.becks.api.share.DTO.BalanceDTO;
import beertech.becks.api.share.DTO.CurrentAccountDTO;
import beertech.becks.api.share.DTO.CurrentAccountTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static beertech.becks.api.constants.Constants.Hash.HASH_MD5;
import static beertech.becks.api.constants.Constants.NUMBERS.NUMBER_1L;
import static beertech.becks.api.entities.builder.CurrentAccountBuilder.aCurrentAccount;
import static beertech.becks.api.entities.builder.CurrentAccountBuilder.getHashInitialDefaut;
import static beertech.becks.api.utils.HashGenerator.generatorHash;

@Service
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

    public CurrentAccount updateCurrentAccountTranscation(CurrentAccountDTO currentAccountDTO) {
        CurrentAccount currentAccount = findCurrentAccountByHash(currentAccountDTO.getHashValue());
        currentAccount.setAccountBalance(currentAccountDTO.getUpdatedBalance());
        return saveCurrentAccount(currentAccount);
    }

    public CurrentAccount doCurrentAccountTransferTranscation(CurrentAccountTransferDTO dto) {

        CurrentAccount currentAccountOrigin = findCurrentAccountByHash(dto.getCurrentAccountIndentify());
        CurrentAccount currentAccountDestiny = findCurrentAccountByHash(dto.getDestinationCurrentAccount());

        updateBalanceAccountOrigin(currentAccountOrigin, dto);
        updateBalanceAccountDestiny(currentAccountDestiny, dto);

        return currentAccountOrigin;
    }

    private void updateBalanceAccountOrigin(CurrentAccount currentAccountOrigin, CurrentAccountTransferDTO dto) {
        Optional<BigDecimal> previosBalance = transactionRepository.getSumValueBalanceByHash(currentAccountOrigin.getHashValue());
        currentAccountOrigin.setAccountBalance(previosBalance.isPresent() ? previosBalance.get().add(dto.getBalance()) : dto.getBalance());
        saveCurrentAccount(currentAccountOrigin);
    }

    private void updateBalanceAccountDestiny(CurrentAccount currentAccountDestiny, CurrentAccountTransferDTO dto) {
        Optional<BigDecimal> previosBalance = transactionRepository.getSumValueBalanceByHash(currentAccountDestiny.getHashValue());
        currentAccountDestiny.setAccountBalance(previosBalance.isPresent() ? previosBalance.get().add(dto.getBalance()) : dto.getBalance());
        saveCurrentAccount(currentAccountDestiny);
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
            return aCurrentAccount().withHashAccount(getHash(newId)).builder();
        }
        return getHashInitialDefaut();
    }

    private String getHash(Long id) {
        return generatorHash(getStringHash(id), HASH_MD5);
    }

    private String getStringHash(Long newIdCurrentBuilder) {
        return newIdCurrentBuilder.toString();
    }

    private Long getIdCurrentAccount(Optional<CurrentAccount> currentAccount) {
        return currentAccount.get().getIdCurrentAccount();
    }

    private Long findLastRegisteredCurrentAccount() {
        return currentAccountRepository.getLastIdCurrentAccount();
    }

}
