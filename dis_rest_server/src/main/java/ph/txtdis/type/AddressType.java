package ph.txtdis.type;

public enum AddressType {
	HOME, WORK, MAIN, DELIVERY, PICK_UP, INVOICING, PAYMENT, NONE;

	@Override
	public String toString() {
		return name().replace("_", "-");
	}
}
