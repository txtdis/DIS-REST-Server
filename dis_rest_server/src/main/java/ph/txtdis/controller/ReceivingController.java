package ph.txtdis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Billing;
import ph.txtdis.repository.BillingRepository;

@RestController("receivingController")
@RequestMapping("/receivings")
public class ReceivingController extends IdController<BillingRepository, Billing, Long> {
}