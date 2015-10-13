package ph.txtdis.repository;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import ph.txtdis.domain.StockTakeReconciliation;

@Repository("stockTakeReconciliationRepository")
public interface StockTakeReconciliationRepository extends SpunRepository<StockTakeReconciliation, LocalDate> {

	// @Query("select max(s.id) from StockTakeReconciliation s where s.id <
	// :date and s.isApproved = true")
	// LocalDate getImmediatelyPrecedingDate(@Param("date") LocalDate date);
	//
	// @Query(" select new ph.txtdis.domain.StockTakeReconciliationDetail(i, q,
	// "
	// + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate =
	// :startDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate =
	// :startDate and i = s.item and q = s.quality), "
	// + "(select sum(s.qty) from ReceivingSummary s where s.orderDate between
	// :startDate and :endDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select sum(s.qty) from PickingSummary s where s.pickDate between
	// :startDate and :endDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate =
	// :endDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate =
	// :endDate and i = s.item and q = s.quality), "
	// + "(select s.justification from StockTakeAdjustment s where
	// s.stockTakeDate = :endDate "
	// + " and i = s.item and q = s.quality)) from Item i, Quality q order by
	// i.id, q.id")
	// List<StockTakeReconciliationDetail> getDetail(
	// @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate
	// endDate);
	//
	// @Query(" select new ph.txtdis.util.TransactionStamp(s.id, s.cutoffBy,
	// s.cutoffOn) from StockTakeReconciliation s "
	// + "where s.id = (select max(st.id) from StockTakeReconciliation st where
	// st.completedBy is null)")
	// TransactionStamp getOnGoingStockTakeCutoffStamp();
	//
	// @Query(" select new ph.txtdis.util.TransactionStamp(s.id, s.completedBy,
	// s.completedOn) "
	// + " from StockTakeReconciliation s where s.id = "
	// + "(select max(st.id) from StockTakeReconciliation st where
	// st.completedBy is not null)")
	// TransactionStamp getLatestCompletedStockTakeCompletionStamp();
	//
	// @Query(" select new ph.txtdis.domain.Inventory(i, q, "
	// + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate =
	// :startDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate =
	// :startDate and i = s.item "
	// + " and q = s.quality), "
	// + "(select sum(s.qty) from ReceivingSummary s where s.orderDate between
	// :startDate and :endDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select sum(s.qty) from PickingSummary s where s.pickDate between
	// :startDate and :endDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select sum(s.qty) from InvoicedVolumeView s where s.orderDate between
	// :cutoffDate and :endDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality)) "
	// + "from Item i, Quality q order by i.id, q.id")
	// List<Inventory> getInventory(
	// @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate
	// endDate,
	// @Param("cutoffDate") LocalDate cutoffDate);
	//
	// @Query(" select new ph.txtdis.domain.Inventory(i, q, "
	// + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate =
	// :countDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate =
	// :countDate and i = s.item "
	// + " and q = s.quality), "
	// + "(select sum(s.qty) from ReceivingSummary s where s.orderDate between
	// :countDate and :currentDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select sum(s.qty) from PickingSummary s where s.pickDate between
	// :countDate and :currentDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality), "
	// + "(select sum(s.qty) from InvoicedVolumeView s where s.orderDate between
	// :cutoffDate and :currentDate "
	// + " and i = s.item and q = s.quality group by s.item, s.quality)) "
	// + "from Item i, Quality q where i = :item and q = :quality ")
	// Inventory getStockOnHand(
	// @Param("countDate") LocalDate countDate, @Param("currentDate") LocalDate
	// currentDate,
	// @Param("cutoffDate") LocalDate cutoffDate, @Param("item") Item item,
	// @Param("quality") QualityType quality);
}
