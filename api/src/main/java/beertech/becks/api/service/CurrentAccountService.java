package beertech.becks.api.service;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.share.DTO.BalanceDTO;
import beertech.becks.api.share.DTO.CurrentAccountDTO;
import beertech.becks.api.share.DTO.CurrentAccountTransferDTO;

public interface CurrentAccountService {

    CurrentAccount createCurrentAccount();

    CurrentAccount saveCurrentAccount(CurrentAccount currentAccount);

    BalanceDTO getAccountBalanceByHash(String hashValue);

    CurrentAccount findCurrentAccountByHash(String hashValue);

    CurrentAccount updateCurrentAccountTranscation(CurrentAccountDTO currentAccountDTO);

    CurrentAccount doCurrentAccountTransferTranscation(CurrentAccountTransferDTO currentAccountTransferDTO);

}
