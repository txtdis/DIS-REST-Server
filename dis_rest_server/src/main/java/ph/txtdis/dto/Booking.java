package ph.txtdis.dto;

import lombok.Data;

@Data
public class Booking {

	private Long id;

	private String customer, location, route;
}
