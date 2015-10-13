package ph.txtdis.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Subselect("select i.partner_id id, "
        + "        i.value - sum(case when d.payment_value is null then cast(0 as numeric(7, 2)) "
        + "             else d.payment_value end) balance, "
        + "        cast ((current_date - i.order_date) as int) - case when c.term is null then 0 else c.term end - "
        + "             case when c.grace_period is null then 0 else c.grace_period end as day"
        + "   from invoicing i left join remittance_detail d on i.id = d.invoicing_id "
        + "        left join credit_detail c on c.id = i.credit_id "
        + "  group by i.id, i.partner_id, i.value, current_date, i.order_date, c.term, c.grace_period "
        + " having i.value - sum(case when d.payment_value is null then cast(0 as numeric(7, 2)) "
        + "        else d.payment_value end) > 0 ")
@Synchronize({ "invoicing", "remittance_detail", "credit_detail" })
@Table(indexes = @Index(columnList = "id, day"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvoiceView {

    @Id
    private long id;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private int day;
}
