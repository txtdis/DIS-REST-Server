package ph.txtdis.repository;

import java.time.ZonedDateTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.LoadSettlement;
import ph.txtdis.domain.Truck;

@Repository("LoadSettlementRepository")
public interface LoadSettlementRepository extends CrudRepository<LoadSettlement, Long> {

	// @Query("select new ph.txtdis.domain.LoadSettlementDetail(i, "
	// + "(select sum(case when s.qty is null then 0.00 else s.qty end) from
	// PickingSummaryByTruck s "
	// + " where s.truck = :truck and s.pickDate = :date and i = s.item group by
	// s.item), "
	// + "(select sum(case when s.qty is null then 0.00 else s.qty end) from
	// InvoicedVolumeByTruckView s "
	// + " where s.truck = :truck and s.orderDate = :date and i = s.item group
	// by s.item), "
	// + "(select sum(case when s.qty is null then 0.00 else s.qty end) from
	// ReceivingSummaryByTruck s "
	// + " where s.truck = :truck and s.orderDate = :date and i = s.item group
	// by s.item), "
	// + "(select s.actionTaken from LoadSettlementAdjustment s where s.truck =
	// :truck and s.pickDate = :date "
	// + " and i = s.item)) from Item i order by i.id ")
	// List<LoadSettlementDetail> getDetail(@Param("truck") Truck truck,
	// @Param("date") LocalDate date);

	LoadSettlement findByTruckAndCreatedOnBetween(@Param("truck") Truck truck,
			@Param("startOfDay") ZonedDateTime startOfDay, @Param("endOfDay") ZonedDateTime endOfDay);
}
