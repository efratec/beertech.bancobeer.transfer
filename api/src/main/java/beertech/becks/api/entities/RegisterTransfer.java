package beertech.becks.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reg_transfer")
public class RegisterTransfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRegisterTransfer;

    @ManyToOne
    @JoinColumn(name = "orig_account")
    private CurrentAccount originAccount;

    @ManyToOne
    @JoinColumn(name = "dest_account")
    private CurrentAccount destinyAccount;

    @Column(name = "trans_date")
    private ZonedDateTime transactionDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterTransfer that = (RegisterTransfer) o;
        return Objects.equals(idRegisterTransfer, that.idRegisterTransfer) &&
                Objects.equals(originAccount, that.originAccount) &&
                Objects.equals(destinyAccount, that.destinyAccount) &&
                Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegisterTransfer, originAccount, destinyAccount, transactionDate);
    }

}
