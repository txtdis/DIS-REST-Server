package ph.txtdis.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ph.txtdis.domain.CreditDetail;
import ph.txtdis.domain.Invoice;

public abstract class ReceivableController {

	protected long daysOver(Invoice i) {
		LocalDate dueDate = i.getOrderDate().plusDays(creditTermsInDays(i));
		return dueDate.until(LocalDate.now(), ChronoUnit.DAYS);
	}

	protected long creditTermsInDays(Invoice i) {
		CreditDetail credit = i.getCredit();
		return credit == null ? 0L : credit.getTermInDays() + credit.getGracePeriodInDays();
	}
}
