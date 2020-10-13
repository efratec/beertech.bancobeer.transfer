package beertech.becks.api.entities;

import beertech.becks.api.model.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_transaction")
public class Transaction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_transaction")
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "operation")
	private TypeOperation typeOperation;

	@Column(name = "val_transaction")
	private BigDecimal valueTransaction;

	@Column(name = "dat_transaction")
	private ZonedDateTime dateTime;

	@ManyToOne
	@JoinColumn(name = "account_hash_value", referencedColumnName = "hash_value")
	private CurrentAccount currentAccount;

	@Column(name = "destination_hash_value")
	private String destinationHashValue;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return Objects.equals(id, that.id) &&
				typeOperation == that.typeOperation &&
				Objects.equals(valueTransaction, that.valueTransaction) &&
				Objects.equals(dateTime, that.dateTime) &&
				Objects.equals(currentAccount, that.currentAccount) &&
				Objects.equals(destinationHashValue, that.destinationHashValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, typeOperation, valueTransaction, dateTime, currentAccount, destinationHashValue);
	}

}
