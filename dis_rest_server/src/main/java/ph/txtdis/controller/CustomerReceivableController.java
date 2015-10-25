package ph.txtdis.controller;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Invoice;
import ph.txtdis.dto.CustomerReceivable;
import ph.txtdis.dto.CustomerReceivableReport;
import ph.txtdis.repository.InvoiceRepository;

@RestController("customerReceivableController")
@RequestMapping("/customerReceivables")
public class CustomerReceivableController extends ReceivableController {

	@Autowired
	private InvoiceRepository repository;

	private List<CustomerReceivable> receivables;

	private List<BigDecimal> totals;

	private String customerName;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> customerReceivableReport(@RequestParam("customer") Customer c,
			@RequestParam("lowerDayCount") long low, @RequestParam("upperDayCount") long up) {
		extractDataFromInvoices(c, low, up);
		CustomerReceivableReport r = new CustomerReceivableReport(receivables, totals, customerName,
				ZonedDateTime.now());
		return new ResponseEntity<>(r == null ? null : r, HttpStatus.OK);
	}

	private void computeTotals(List<CustomerReceivable> receivables) {
		totals = new ArrayList<>(2);
		for (int i = 0; i < 2; i++)
			totals.add(BigDecimal.ZERO);
		receivables.forEach(r -> {
			totals.set(0, totals.get(0).add(r.getTotalValue()));
			totals.set(1, totals.get(1).add(r.getUnpaidValue()));
		});
	}

	private void extractDataFromInvoices(Customer c, long low, long up) {
		List<Invoice> list = repository.findByCustomerAndFullyPaidOrderByOrderDateDesc(c, false);
		if (list != null)
			setData(low, up, list);
	}

	private void generateReceivableList(long low, long up, List<Invoice> list) {
		receivables = list.stream().filter(i -> daysOver(i) >= low && daysOver(i) <= up)
				.map(i -> new CustomerReceivable(i)).collect(Collectors.toList());
	}

	private void setCustomer(List<Invoice> list) {
		if (list.isEmpty())
			return;
		String n = list.get(0).getCustomer().getName();
		customerName = WordUtils.capitalizeFully(n);
	}

	private void setData(long low, long up, List<Invoice> list) {
		generateReceivableList(low, up, list);
		setCustomer(list);
		computeTotals(receivables);
	}
}