package ph.txtdis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.repository.BillingRepository;

@Component("customerService")
public class CustomerService {

	@Autowired
	private BillingRepository repository;

	public void deactivateNonBuyingOutlets() {
	}
}
