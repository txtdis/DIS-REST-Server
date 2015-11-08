package ph.txtdis.service;

import static java.util.stream.Collectors.toList;
import static ph.txtdis.util.NumberUtils.isNegative;
import static ph.txtdis.util.NumberUtils.isPositive;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.math.BigDecimal.ZERO;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Remittance;
import ph.txtdis.domain.RemittanceDetail;
import ph.txtdis.dto.Payment;
import ph.txtdis.dto.PaymentDetail;
import ph.txtdis.repository.BillingRepository;
import ph.txtdis.repository.CustomerRepository;
import ph.txtdis.repository.RemittanceRepository;

@Service("paymentToRemittanceConverter")
public class PaymentToRemittanceConverter {

	@Autowired
	private BillingRepository billing;

	@Autowired
	private CustomerRepository customer;

	@Autowired
	private RemittanceRepository remit;

	public Remittance toRemittance(Payment p) {
		return p == null ? null : convert(p);
	}

	private Billing billing(PaymentDetail p) {
		Billing b = billing.findOne(p.getId());
		BigDecimal bal = unpaid(b).subtract(p.getPaymentValue());
		b.setUnpaidValue(bal);
		b.setFullyPaid(!isPositive(bal));
		return b;
	}

	private Remittance convert(Payment p) {
		return p.getId() == null ? create(p) : update(p);
	}

	private Remittance create(Payment p) {
		Remittance r = new Remittance();
		r.setPaymentDate(p.getPaymentDate());
		r.setValue(p.getValue());
		r.setCollector(p.getCollector());
		r.setRemarks(p.getRemarks());
		r.setDetails(details(p));
		if (p.getCheckId() != null)
			setCheckData(p, r);
		return r;
	}

	private Customer depositorBank(Payment p) {
		return customer.findOne(p.getDepositorBankId());
	}

	private RemittanceDetail detail(PaymentDetail p) {
		RemittanceDetail r = new RemittanceDetail();
		r.setBilling(billing(p));
		r.setPaymentValue(payment(p));
		return r;
	}

	private List<RemittanceDetail> details(Payment p) {
		return p.getDetails().stream().map(d -> detail(d)).collect(toList());
	}

	private Customer draweeBank(Payment p) {
		return customer.findOne(p.getDraweeBankId());
	}

	private BigDecimal payment(PaymentDetail d) {
		BigDecimal p = d.getPaymentValue();
		return isNegative(p) ? ZERO : p;
	}

	private Customer payor(Payment p) {
		return customer.findOne(p.getPayorId());
	}

	private void setAuditData(Payment p, Remittance r) {
		boolean isValid = p.getIsValid();
		if (!isValid)
			r.setDetails(details(p));
		r.setIsValid(isValid);
		r.setAuditedBy(p.getAuditedBy());
		r.setAuditedOn(p.getAuditedOn());
		r.setRemarks(p.getRemarks());
	}

	private void setCheckData(Payment p, Remittance r) {
		r.setCheckId(p.getCheckId());
		r.setAccountNo(p.getAccountNo());
		r.setDraweeBank(draweeBank(p));
		r.setPayor(payor(p));
	}

	private void setDepositData(Payment p, Remittance r) {
		r.setDepositedOn(p.getDepositedOn());
		r.setDepositor(p.getDepositor());
		r.setDepositorBank(depositorBank(p));
		r.setDepositorOn(p.getDepositorOn());
	}

	private void setTransferData(Payment p, Remittance r) {
		r.setReceivedBy(p.getReceivedBy());
		r.setReceivedOn(p.getReceivedOn());
	}

	private BigDecimal unpaid(Billing b) {
		BigDecimal u = b.getUnpaidValue();
		return u == null ? ZERO : u;
	}

	private Remittance update(Payment p) {
		Remittance r = remit.findOne(p.getId());
		if (p.getIsValid() != null)
			setAuditData(p, r);
		if (p.getReceivedOn() != null)
			setTransferData(p, r);
		if (p.getDepositorOn() != null)
			setDepositData(p, r);
		return r;
	}
}
