package ph.txtdis.repository;

import java.time.ZonedDateTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.RemittanceSettlement;
import ph.txtdis.domain.Truck;

@Repository("remittanceSettlementRepository")
public interface RemittanceSettlementRepository extends CrudRepository<RemittanceSettlement, Long> {

	// @Query("select new ph.txtdis.domain.RemittanceSettlementDetail(i, "
	// + "(select sum(rd.paymentValue) from RemittanceDetail rd where i =
	// rd.invoicing), "
	// + "(select s.actionTaken from RemittanceSettlementAdjustment s where i =
	// s.invoice)) "
	// + "from Invoice i, Picking p, PickingDetail pd "
	// + "where p = pd.picking and i.booking = pd.booking and p.truck = :truck
	// and i.orderDate = :date "
	// + "order by i.id ")
	// List<RemittanceSettlementDetail> getDetail(@Param("truck") Truck truck,
	// @Param("date") LocalDate date);

	RemittanceSettlement findByTruckAndCreatedOnBetween(@Param("truck") Truck truck,
			@Param("startOfDay") ZonedDateTime startOfDay, @Param("endOfDay") ZonedDateTime endOfDay);
}
