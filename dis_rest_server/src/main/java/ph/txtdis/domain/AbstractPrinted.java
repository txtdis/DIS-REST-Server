package ph.txtdis.domain;

import java.time.ZonedDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractPrinted extends SoldOrder<BookingDetail, BookingDiscount> {

	private static final long serialVersionUID = 4553686125204430154L;

	@ManyToOne
	private User printedBy;

	private ZonedDateTime printedOn;
}
