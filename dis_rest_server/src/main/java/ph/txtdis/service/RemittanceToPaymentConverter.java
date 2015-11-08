package ph.txtdis.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Remittance;
import ph.txtdis.domain.RemittanceDetail;
import ph.txtdis.dto.Payment;
import ph.txtdis.dto.PaymentDetail;

@Service("remittanceToPaymentConverter")
public class RemittanceToPaymentConverter {

	public Payment toPayment(Remittance p) {
		return p == null ? null : convert(p);
	}

	private Billing billing(RemittanceDetail r) {
		return r.getBilling();
	}

	private Payment convert(Remittance r) {
		Payment p = new Payment();
		p.setId(r.getId());
		p.setValue(r.getValue());
		p.setDetails(details(r));
		p.setPaymentDate(r.getPaymentDate());
		p.setRemarks(r.getRemarks());
		p.setCollector(r.getCollector());
		p.setCreatedBy(r.getCreatedBy());
		p.setCreatedOn(r.getCreatedOn());
		if (r.getIsValid() != null)
			setAuditData(r, p);
		if (r.getDepositorOn() != null)
			setDepositData(r, p);
		if (r.getReceivedOn() != null)
			setTransferData(r, p);
		if (r.getCheckId() != null)
			serCheckData(r, p);
		return p;
	}

	private Customer depositorBank(Remittance r) {
		return r.getDepositorBank();
	}

	private Long depositorBankId(Remittance r) {
		return depositorBank(r) == null ? null : depositorBank(r).getId();
	}

	private String depositorBankName(Remittance r) {
		return depositorBank(r) == null ? null : depositorBank(r).getName();
	}

	private PaymentDetail detail(RemittanceDetail r) {
		PaymentDetail p = new PaymentDetail();
		p.setId(billing(r).getId());
		p.setOrderNo(billing(r).getOrderNo());
		p.setCustomerName(billing(r).getCustomer().getName());
		p.setDueDate(billing(r).getDueDate());
		p.setTotalDueValue(billing(r).getTotalValue());
		p.setPaymentValue(r.getPaymentValue());
		return p;
	}

	private List<PaymentDetail> details(Remittance r) {
		return r.getDetails().stream().map(d -> detail(d)).collect(toList());
	}

	private Customer draweeBank(Remittance r) {
		return r.getDraweeBank();
	}

	private Long draweeBankId(Remittance r) {
		return draweeBank(r) == null ? null : draweeBank(r).getId();
	}

	private String draweeBankName(Remittance r) {
		return draweeBank(r) == null ? null : draweeBank(r).getName();
	}

	private Customer payor(Remittance r) {
		return r.getPayor();
	}

	private Long payorId(Remittance r) {
		return payor(r) == null ? null : payor(r).getId();
	}

	private String payorName(Remittance r) {
		return payor(r) == null ? null : payor(r).getName();
	}

	private void serCheckData(Remittance r, Payment p) {
		p.setCheckId(r.getCheckId());
		p.setAccountNo(r.getAccountNo());
		p.setDraweeBankId(draweeBankId(r));
		p.setDraweeBank(draweeBankName(r));
		p.setPayorId(payorId(r));
		p.setPayor(payorName(r));
	}

	private void setAuditData(Remittance r, Payment p) {
		p.setIsValid(r.getIsValid());
		p.setAuditedBy(r.getAuditedBy());
		p.setAuditedOn(r.getAuditedOn());
	}

	private void setDepositData(Remittance r, Payment p) {
		p.setDepositorBankId(depositorBankId(r));
		p.setDepositorBank(depositorBankName(r));
		p.setDepositedOn(r.getDepositedOn());
		p.setDepositor(r.getDepositor());
		p.setDepositorOn(r.getDepositorOn());
	}

	private void setTransferData(Remittance r, Payment p) {
		p.setReceivedBy(r.getReceivedBy());
		p.setReceivedOn(r.getReceivedOn());
	}
}
