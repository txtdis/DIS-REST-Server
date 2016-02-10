package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Billable extends EntityDecisionNeeded<Long> {

	private boolean isPicked;

	private Boolean isRma, isValid;

	private BigDecimal badOrderAllowanceValue, grossValue, totalValue, unpaidValue;

	private List<BillableDetail> details;

	private List<Long> discountIds;

	private List<String> discounts, payments;

	private LocalDate dueDate, orderDate;

	private Long id, numId, bookingId, customerId, receivingId;

	private String prefix, suffix, customerName, customerAddress, customerLocation, remarks, route, billedBy, printedBy,
			receivedBy, receivingModifiedBy, truck;

	private ZonedDateTime billedOn, printedOn, receivedOn, receivingModifiedOn;
}
