package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Invoice;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemittanceSettlementDetail {

    private Invoice invoice;

    private BigDecimal payment;

    private String actionTaken;
}
