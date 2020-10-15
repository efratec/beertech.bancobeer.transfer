package beertech.becks.api.entities.builder;

import beertech.becks.api.entities.CurrentAccount;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.model.enums.TypeOperation;
import beertech.becks.api.share.DTO.TransactionRequestDTO;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static beertech.becks.api.model.enums.TypeOperation.getTypeOperationByDescription;
import static java.time.ZonedDateTime.now;

public final class TransactionBuilder implements Builder<Transaction> {

    private final Transaction transaction = new Transaction();

    private TransactionBuilder() {
    }

    public static TransactionBuilder aTransaction() {
        return new TransactionBuilder();
    }

    public TransactionBuilder withId(Long id) {
        this.transaction.setId(id);
        return this;
    }

    public TransactionBuilder withOperation(TypeOperation operation) {
        this.transaction.setTypeOperation(operation);
        return this;
    }

    public TransactionBuilder withValueTransaction(BigDecimal valueTransaction) {
        this.transaction.setValueTransaction(valueTransaction);
        return this;
    }

    public TransactionBuilder withDateTime(ZonedDateTime dateTime) {
        this.transaction.setDateTime(dateTime);
        return this;
    }

    public TransactionBuilder withCurrentAccount(CurrentAccount currentAccount) {
        this.transaction.setCurrentAccount(currentAccount);
        return this;
    }

    public TransactionBuilder withDestinationHashValue(String destinationHashValue) {
        this.transaction.setDestinationHashValue(destinationHashValue);
        return this;
    }

    @Override
    public Transaction builder() {
        return this.transaction;
    }

}
