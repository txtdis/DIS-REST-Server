package ph.txtdis.printer;

import static ph.txtdis.util.NumberUtils.formatQuantity;

import java.io.IOException;

import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.center;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.BillingDetail;
import ph.txtdis.domain.Picking;
import ph.txtdis.util.DateTimeUtils;
import ph.txtdis.util.NumberUtils;

@Component("pickListPrinter")
public class LoadOrderPrinter extends Printer<Picking> {

	private String itemId(BillingDetail d) {
		return leftPad(NumberUtils.formatId(d.getItem().getId()), 6);
	}

	private String itemName(BillingDetail d) {
		return rightPad(d.getItem().getName(), 19);
	}

	private String itemQty(BillingDetail d) {
		return leftPad(formatQuantity(d.getQty()), 3);
	}

	private void printBookings(Billing b) throws IOException {
		printLarge();
		printer.println(center("S/O #" + b.getId(), COLUMN_WIDTH / 2));
		printNormal();
		printDashes();
		for (BillingDetail d : b.getDetails())
			printer.println(itemQty(d) + d.getUom() + " " + itemName(d) + itemId(d) + " ____  ____");
		printDashes();
	}

	private String truck() {
		return entity.getTruck().getName();
	}

	@Override
	protected void printDetails() throws IOException {
		for (Billing b : entity.getBookings())
			printBookings(b);
	}

	@Override
	protected void printFooter() {
		printer.println();
		printEndOfPage();
	}

	@Override
	protected void printSubheader() throws IOException {
		printer.println("DATE    : " + DateTimeUtils.formatDate(entity.getPickDate()));
		printer.println("LOAD TO : " + truck());
		printer.println("LOAD-OUT:");
		printer.println("_____________  _____________  ____________");
		printer.println(" STOREKEEPER      CHECKER        DRIVER");
		printer.println();
		printer.println("BACKLOAD:");
		printer.println("_____________  _____________  ____________");
		printer.println("   DRIVER         CHECKER      STOREKEEPER");
		printer.println("PICKLIST #" + entity.getId());
		printDashes();
		printer.println("  QTY    DESCRIPTION     CODE    OUT    IN");
		printDashes();
	}
}
