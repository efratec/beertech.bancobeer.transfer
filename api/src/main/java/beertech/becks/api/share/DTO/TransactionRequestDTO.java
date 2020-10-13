package beertech.becks.api.share.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

	private String operation;
	private BigDecimal value;
	private String originAccount;
	private String destinationAccount;

}
