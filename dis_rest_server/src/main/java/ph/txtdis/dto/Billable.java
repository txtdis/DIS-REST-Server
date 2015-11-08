package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Keyed;

@Data
@NoArgsConstructor
public class Billable implements Keyed<Long> {

	private Boolean isValid;

	private BigDecimal actualValue, grossValue, totalValue, unpaidValue;

	private List<BillableDetail> details;

	private List<Long> discountIds, detailIds;

	private List<String> discounts, payments;

	private LocalDate dueDate, orderDate;

	private Long id, numId, bookingId, customerId;

	private String prefix, suffix, customerName, customerAddress, remarks, createdBy, auditedBy;

	private ZonedDateTime createdOn, auditedOn;

	public Billable(Billing b) {
		id = b.getId();
		numId = b.getNumId();
		bookingId = b.getBookingId();
		customerId = b.getCustomer().getId();
		prefix = b.getPrefix();
		suffix = b.getSuffix();
		customerName = b.getCustomer().getName();
		customerAddress = b.getCustomer().getAddress();
		remarks = b.getRemarks();
		createdBy = b.getCreatedBy();
		auditedBy = b.getAuditedBy();
		dueDate = b.getDueDate();
		orderDate = b.getOrderDate();
		actualValue = b.getActualValue();
		grossValue = b.getGrossValue();
		totalValue = b.getTotalValue();
		unpaidValue = b.getUnpaidValue();
		createdOn = b.getCreatedOn();
		auditedOn = b.getAuditedOn();
		isValid = b.getIsValid();
	}
}
