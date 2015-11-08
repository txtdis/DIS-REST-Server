package ph.txtdis.service;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static ph.txtdis.util.NumberUtils.formatCurrency;
import static ph.txtdis.util.NumberUtils.toPercentRate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.math.BigDecimal.ZERO;

import static ph.txtdis.util.DateTimeUtils.formatDate;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Discount;
import ph.txtdis.domain.Remittance;
import ph.txtdis.domain.RemittanceDetail;
import ph.txtdis.dto.Billable;
import ph.txtdis.dto.BillableDetail;
import ph.txtdis.repository.RemittanceDetailRepository;

@Service("billingToInvoiceConverter")
public class BillingToBillableConverter {

	@Autowired
	private RemittanceDetailRepository remitDetailRepo;

	public Billable toBillable(Billing b) {
		return b == null ? null : convert(b);
	}

	private Billable convert(Billing b) {
		Billable i = new Billable(b);
		i.setPayments(payments(b));
		i.setDiscounts(discounts(b));
		i.setDetails(details(b));
		return i;
	}

	private String createEachLevelDiscountText(Discount d, BigDecimal total, BigDecimal net) {
		BigDecimal perLevel = net.multiply(toPercentRate(d.getPercent()));
		total = total.add(perLevel);
		net = net.subtract(total);
		return "[" + d.getLevel() + ": " + d.getPercent() + "%] " + formatCurrency(perLevel);
	}

	private String createRemitIdAndDateAndPaymentText(RemittanceDetail p) {
		Remittance r = p.getRemittance();
		return "[" + status(r) + ": " + r + " - " + formatDate(r.getPaymentDate()) + "] "
				+ formatCurrency(p.getPaymentValue());
	}

	private List<BillableDetail> details(Billing b) {
		try {
			return b.getDetails().stream().map(d -> new BillableDetail(d)).collect(toList());
		} catch (Exception e) {
			return emptyList();
		}
	}

	private List<String> discounts(Billing b) {
		try {
			List<Discount> d = b.getDiscounts();
			return d.isEmpty() ? emptyList() : listDiscounts(b);
		} catch (Exception e) {
			return emptyList();
		}
	}

	private BigDecimal discountValue(Billing b) {
		try {
			return b.getGrossValue().subtract(b.getTotalValue());
		} catch (Exception e) {
			return ZERO;
		}
	}

	private List<String> getEachLevelDiscountTextList(Billing b, List<String> list) {
		BigDecimal net = b.getGrossValue();
		b.getDiscounts().forEach(d -> list.add(createEachLevelDiscountText(d, ZERO, net)));
		return list;
	}

	private List<String> getRemitIdAndDateAndPaymentTextList(List<RemittanceDetail> r, List<String> list) {
		r.forEach(p -> list.add(createRemitIdAndDateAndPaymentText(p)));
		return list;
	}

	private String getTotalInText(BigDecimal t) {
		return "[TOTAL] " + formatCurrency(t);
	}

	private Boolean isValid(RemittanceDetail d) {
		try {
			return d.getRemittance().getIsValid();
		} catch (Exception e) {
			return false;
		}
	}

	private List<String> listDiscounts(Billing b) {
		ArrayList<String> list = new ArrayList<>();
		if (b.getDiscounts().size() > 1)
			list.add(getTotalInText(discountValue(b)));
		return getEachLevelDiscountTextList(b, list);
	}

	private List<String> listPayments(List<RemittanceDetail> p) {
		ArrayList<String> list = new ArrayList<>();
		if (p.size() > 1)
			list.add(getTotalInText(sumPayments(p)));
		return getRemitIdAndDateAndPaymentTextList(p, list);
	}

	private List<String> payments(Billing b) {
		try {
			List<RemittanceDetail> p = remitDetailRepo.findByBilling(b);
			return p.isEmpty() ? emptyList() : listPayments(p);
		} catch (Exception e) {
			return emptyList();
		}
	}

	private String status(Remittance r) {
		Boolean b = r.getIsValid();
		if (b == null)
			return "PENDING";
		if (b)
			return "VALID";
		return "INVALID";
	}

	private BigDecimal sumPayments(List<RemittanceDetail> payments) {
		try {
			return payments.stream().filter(d -> isValid(d)).map(d -> d.getPaymentValue()).reduce(ZERO,
					(a, b) -> a.add(b));
		} catch (Exception e) {
			return ZERO;
		}
	}
}
