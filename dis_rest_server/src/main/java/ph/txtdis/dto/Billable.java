package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;
import ph.txtdis.domain.Keyed;

@Data
public class Billable implements Keyed<Long> {

	private boolean isPicked;

	private Boolean isRma, isValid;

	private BigDecimal badOrderAllowanceValue, grossValue, totalValue, unpaidValue;

	private List<BillableDetail> details;

	private List<Long> discountIds;

	private List<String> discounts, payments;

	private LocalDate dueDate, orderDate;

	private Long id, numId, bookingId, customerId, receivingId;

	private String prefix, suffix, customerName, customerAddress, customerLocation, remarks, route, auditedBy, billedBy,
			createdBy, printedBy, receivedBy, receivingModifiedBy, truck;

	private ZonedDateTime auditedOn, billedOn, createdOn, printedOn, receivedOn, receivingModifiedOn;
}
