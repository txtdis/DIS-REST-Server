package ph.txtdis.repository;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import ph.txtdis.domain.DailyStockTakeSummary;

@Repository("summaryRepository")
public interface SummaryRepository extends SpunRepository<DailyStockTakeSummary, LocalDate> {

	// @Query("select new ph.txtdis.domain.VolumeSummary(s.item, sum(s.qty))
	// from InvoicedVolumeView s "
	// + "where s.orderDate = :date group by s.item ")
	// List<VolumeSummary> getVolumeSummary(@Param("date") LocalDate date);
	//
	// @Query("select new ph.txtdis.domain.VolumeSummary(s.item, sum(s.qty))
	// from InvoicedVolumeByTruckView s "
	// + "where s.orderDate = :date and s.truck = :truck group by s.item ")
	// List<VolumeSummary> getVolumeSummary(@Param("date") LocalDate date,
	// @Param("truck") Truck truck);
}
