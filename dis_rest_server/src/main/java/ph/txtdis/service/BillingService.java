package ph.txtdis.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.math.BigDecimal.ZERO;

import static ph.txtdis.util.DateTimeUtils.toDate;

import ph.txtdis.domain.Billing;
import ph.txtdis.repository.BillingRepository;

@Service("billingService")
public class BillingService implements DecisionDataUpdate {

	@Autowired
	private BillingRepository repository;

	@Autowired
	private RemittanceService service;

	public void updateDecisionData(String[] s) {
		Billing b = updateDecisionData(repository, s);
		if (b.getIsValid() != null && !b.getIsValid()) {
			b.setBilledBy(null);
			b.setBilledOn(null);
			repository.save(b);
			// List<Remittance> l = service.findByBilling(b);
			// if (l != null) {
			// String[] rs = s.clone();
			// rs[0] = PAYMENT_VALIDATION.toString();
			// for (Remittance r : l) {
			// rs[1] = r.getId().toString();
			// service.updateDeposit(rs);
			// }
			// }
			// service.updatePaymentValidation(s);
		}
	}

	public void updateItemReturnPayment(String[] s) {
		// TODO Auto-generated method stub
		Billing b = repository.findOne(Long.valueOf(s[1]));
		b.setOrderDate(toDate(s[2]));
		b.setBilledBy(s[3]);
		b.setBilledOn(ZonedDateTime.parse(s[4]));
		b.setUnpaidValue(ZERO);
		repository.save(b);
	}
}
