package beertech.becks.api.entities.builder;

import beertech.becks.api.entities.CurrentAccount;

import java.math.BigDecimal;

import static beertech.becks.api.constants.Constants.Hash.HASH_MD5;
import static beertech.becks.api.constants.Constants.NUMBERS.NUMBER_1L;
import static beertech.becks.api.utils.HashGenerator.generatorHash;
import static beertech.becks.api.utils.HashGenerator.getStringHash;

public final class CurrentAccountBuilder implements Builder<CurrentAccount> {

    private CurrentAccount currentAccount = new CurrentAccount();

    private CurrentAccountBuilder() {
    }

    public static CurrentAccountBuilder aCurrentAccount() {
        return new CurrentAccountBuilder();
    }

    public CurrentAccountBuilder withIdCurrentAccount(Long idCurrentAccount) {
        this.currentAccount.setIdCurrentAccount(idCurrentAccount);
        return this;
    }

    public CurrentAccountBuilder withHashAccount(String hashValue) {
        this.currentAccount.setHashValue(hashValue);
        return this;
    }
    public CurrentAccountBuilder withAccountBalance(BigDecimal accountBalance) {
        this.currentAccount.setAccountBalance(accountBalance);
        return this;
    }

    public static CurrentAccount getHashInitialDefaut() {
        return aCurrentAccount().withHashAccount(generatorHash(getStringHash(NUMBER_1L), HASH_MD5)).builder();
    }

    @Override
    public CurrentAccount builder() {
        return this.currentAccount;
    }

}
