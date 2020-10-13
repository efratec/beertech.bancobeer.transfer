package beertech.becks.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "current_account")
public class CurrentAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long idCurrentAccount;

    @Column(name = "hash_value", unique = true)
    private String hashValue;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentAccount that = (CurrentAccount) o;
        return Objects.equals(idCurrentAccount, that.idCurrentAccount) &&
                Objects.equals(hashValue, that.hashValue) &&
                Objects.equals(accountBalance, that.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurrentAccount, hashValue, accountBalance);
    }

}
