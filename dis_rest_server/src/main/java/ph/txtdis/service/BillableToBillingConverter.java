package ph.txtdis.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Discount;
import ph.txtdis.domain.BillingDetail;
import ph.txtdis.dto.Billable;
import ph.txtdis.repository.CustomerRepository;
import ph.txtdis.repository.DiscountRepository;
import ph.txtdis.repository.BillingRepository;
import ph.txtdis.repository.SoldDetailRepository;

@Service("invoiceToBillingConverter")
public class BillableToBillingConverter {

	@Autowired
	private CustomerRepository customer;

	@Autowired
	private DiscountRepository discount;

	@Autowired
	private BillingRepository invoice;

	@Autowired
	private SoldDetailRepository detail;

	public Billing toBilling(Billable b) {
		return b == null ? null : convert(b);
	}

	private Billing convert(Billable i) {
		return i.getId() == null ? create(i) : update(i);
	}

	private Billing create(Billable i) {
		Billing b = new Billing();
		b.setNumId(i.getNumId());
		b.setBookingId(i.getBookingId());
		b.setPrefix(i.getPrefix());
		b.setSuffix(i.getSuffix());
		b.setCustomer(customer(i));
		b.setOrderDate(i.getOrderDate());
		b.setDueDate(i.getDueDate());
		b.setUnpaidValue(i.getUnpaidValue());
		b.setDiscounts(discounts(i));
		b.setDetails(details(i));
		b.setActualValue(i.getActualValue());
		b.setGrossValue(i.getGrossValue());
		b.setTotalValue(i.getTotalValue());
		b.setRemarks(i.getRemarks());
		return b;
	}

	private Customer customer(Billable i) {
		return customer.findOne(i.getCustomerId());
	}

	private List<BillingDetail> details(Billable i) {
		return i.getDetailIds().stream().map(id -> detail.findOne(id)).collect(toList());
	}

	private List<Discount> discounts(Billable i) {
		return i.getDiscountIds().stream().map(id -> discount.findOne(id)).collect(toList());
	}

	private Billing update(Billable i) {
		Billing b = invoice.findOne(i.getId());
		b.setIsValid(i.getIsValid());
		b.setRemarks(i.getRemarks());
		return b;
	}
}
