package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Channel;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Subselect("select ch.id, i.order_date, cm.channel_id, r.name, sum(d.qty * qpu.qty / cs.qty) qty from invoicing i "
		+ "join customer cm on i.partner_id = cm.id join channel ch on cm.channel_id = ch.id "
		+ "join invoicing_detail d on i.id = d.invoicing_id join route r on i.route_id = r.id "
		+ "join qty_per_uom qpu on d.item_id = qpu.item_id and qpu.uom = d.uom "
		+ "join qty_per_uom cs on d.item_id = cs.item_id and qpu.uom = 1 "
		+ "group by ch.id, cm.channel_id, i.order_date, r.name ")
@Synchronize({ "invoicing", "invoicing_detail", "item", "channel", "route", "qty_per_uom" })
public class InvoicedVolumePerChannelByRouteView {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private LocalDate orderDate;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Channel channel;

	private String name;

	@Column(nullable = false, precision = 10, scale = 4)
	private BigDecimal qty;
}
