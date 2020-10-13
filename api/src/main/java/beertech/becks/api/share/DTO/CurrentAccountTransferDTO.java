package beertech.becks.api.share.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentAccountTransferDTO {

    private String currentAccountIndentify;

    private BigDecimal balance;

    private String destinationCurrentAccount;

}
