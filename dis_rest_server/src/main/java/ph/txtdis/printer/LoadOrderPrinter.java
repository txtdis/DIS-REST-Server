package ph.txtdis.printer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import static ph.txtdis.util.Numeric.formatQuantity;

import static org.apache.commons.lang3.StringUtils.center;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

import ph.txtdis.domain.Booking;
import ph.txtdis.domain.PickList;
import ph.txtdis.domain.SoldDetail;
import ph.txtdis.util.Numeric;
import ph.txtdis.util.Temporal;

@Component("pickListPrinter")
public class LoadOrderPrinter extends Printer<PickList> {

	private String itemId(SoldDetail d) {
		return leftPad(Numeric.formatId(d.getItem().getId()), 6);
	}

	private String itemName(SoldDetail d) {
		return rightPad(d.getItem().getName(), 19);
	}

	private String itemQty(SoldDetail d) {
		return leftPad(formatQuantity(d.getQty()), 3);
	}

	private void printBookings(Booking b) throws IOException {
		printLarge();
		printer.println(center("S/O #" + b.getId(), COLUMN_WIDTH / 2));
		printNormal();
		printDashes();
		for (SoldDetail d : b.getDetails())
			printer.println(itemQty(d) + d.getUom() + " " + itemName(d) + itemId(d) + " ____  ____");
		printDashes();
	}

	private String truck() {
		return entity.getTruck().getName();
	}

	@Override
	protected void printDetails() throws IOException {
		for (Booking b : entity.getBookings())
			printBookings(b);
	}

	@Override
	protected void printFooter() {
		printer.println();
		printEndOfPage();
	}

	@Override
	protected void printSubheader() throws IOException {
		printer.println("DATE    : " + Temporal.formatDate(entity.getPickDate()));
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
