package ph.txtdis.printer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static ph.txtdis.util.Numeric.formatPrint;
import static ph.txtdis.util.Numeric.formatQuantity;

import static org.apache.commons.lang3.StringUtils.center;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.substring;

import ph.txtdis.domain.Booking;
import ph.txtdis.domain.CreditDetail;
import ph.txtdis.domain.Discount;
import ph.txtdis.domain.SoldDetail;
import ph.txtdis.util.Numeric;
import ph.txtdis.util.Temporal;

@Component("salesOrderPrinter")
public class SalesOrderPrinter extends Printer<Booking> {

	private static final int HALF_COLUMN = (COLUMN_WIDTH / 2) - 2;

	private static final int SUBHEADER_LABEL_WIDTH = 9;

	@Value("${invoice.line.item.count}")
	private String linesPerPage;

	@Value("${vat.percent}")
	private String vatValue;

	@Value("${client.initials}")
	private String clientInitials;

	private String customer() {
		String s = entity.getCustomerName();
		return s.length() <= 21 ? s : substring(entity.getCustomerName(), 0, 21);
	}

	private String discount() {
		return Numeric.isZero(discountValue()) ? "--" : formatPrint(discountValue());
	}

	private BigDecimal discountValue() {
		return entity.getDiscountValue();
	}

	private String due() {
		int i = term();
		return "DUE " + (i == 0 ? "TODAY" : dueDate(i));
	}

	private String dueDate(int i) {
		LocalDate d = entity.getOrderDate().plusDays(i);
		return Temporal.formatDate(d);
	}

	private String getPrice(SoldDetail detail) {
		return formatPrint(detail.getPriceValue());
	}

	private int getRemaingLines() {
		return linesPerPage() - entity.getDetails().size();
	}

	private String getSubtotal(SoldDetail detail) {
		return formatPrint(detail.getQty().multiply(detail.getPriceValue()));
	}

	private String gross() {
		return formatPrint(grossValue());
	}

	private BigDecimal grossValue() {
		return entity.getGrossValue();
	}

	private String itemName(SoldDetail detail) {
		return rightPad(detail.getItem().getName(), 19);
	}

	private String itemQty(SoldDetail detail) {
		return leftPad(formatQuantity(detail.getQty()), 3);
	}

	private int linesPerPage() {
		return Integer.valueOf(linesPerPage);
	}

	private String location() {
		String s = entity.getCustomer().getBarangay() + ", " + entity.getCustomer().getCity();
		return StringUtils.substring(s, 0, COLUMN_WIDTH - SUBHEADER_LABEL_WIDTH);
	}

	private String percent() {
		return percents("", entity.getDiscounts());
	}

	private String percents(String s, List<Discount> c) {
		for (Discount d : c)
			s += d.getPercent() + "% * ";
		return StringUtils.removeEnd(s, " * ");
	}

	private String price(SoldDetail detail) {
		return leftPad(getPrice(detail) + "@", 8);
	}

	private void printNothingFollowsFollowedByBlanks() {
		printer.println(center("** NOTHING FOLLOWS **", COLUMN_WIDTH));
		for (int i = 0; i < getRemaingLines(); i++)
			printer.println();
	}

	private String subtotal(SoldDetail detail) {
		return leftPad(getSubtotal(detail), 9);
	}

	private int term() {
		CreditDetail c = entity.getCredit();
		return c == null ? 0 : c.getTermInDays();
	}

	private String total() {
		return formatPrint(totalValue());
	}

	private BigDecimal totalValue() {
		return entity.getValue();
	}

	private String uom(SoldDetail detail) {
		return detail.getUom() + " ";
	}

	private String vat() {
		BigDecimal vat = totalValue().subtract(vatableValue());
		return formatPrint(vat);
	}

	private String vatable() {
		return formatPrint(vatableValue());
	}

	private BigDecimal vatableValue() {
		return Numeric.divide(totalValue(), new BigDecimal("1." + vatValue));
	}

	@Override
	protected void printDetails() {
		for (SoldDetail d : entity.getDetails())
			printer.println(itemQty(d) + uom(d) + itemName(d) + price(d) + subtotal(d));
		if (getRemaingLines() > 0)
			printNothingFollowsFollowedByBlanks();
	}

	@Override
	protected void printFooter() {
		printer.println(leftPad("--------", COLUMN_WIDTH));
		printer.println(leftPad("TOTAL", 33) + leftPad(gross(), 9));
		printer.println(leftPad(percent() + " LESS", 24) + leftPad(discount(), 18));
		printer.println(leftPad("VATABLE", 24) + leftPad(vatable(), 9) + leftPad("--", 9));
		printer.println(leftPad(vatValue + "% VAT", 24) + leftPad(vat(), 9) + leftPad("--", 9));
		printer.println(leftPad("--------", COLUMN_WIDTH));
		printer.println(leftPad("NET", 33) + leftPad(total(), 9));
		printer.println(leftPad("========", COLUMN_WIDTH));
		printer.println();
		printer.println(leftPad(center("PREPARED BY:", HALF_COLUMN), 4) + center("RECEIVED BY:", HALF_COLUMN));
		printer.println(leftPad(leftPad("", HALF_COLUMN, "_"), 4) + leftPad("", HALF_COLUMN, "_"));
		printer.println(leftPad(center(clientInitials, HALF_COLUMN), 4) + center(customer(), HALF_COLUMN));
		printer.println();
		printer.println("ORDER #" + entity.getId());
		printEndOfPage();
	}

	@Override
	protected void printSubheader() throws IOException {
		printer.println("DATE   : " + Temporal.formatDate(entity.getOrderDate()));
		printer.println("SOLD TO: " + customer());
		printer.println("ADDRESS: " + entity.getCustomer().getStreet());
		printer.println("         " + location());
		printLarge();
		printer.println(center(due(), HALF_COLUMN));
		printNormal();
		printDashes();
		printer.println(center("PARTICULARS", COLUMN_WIDTH));
		printDashes();
	}
}
