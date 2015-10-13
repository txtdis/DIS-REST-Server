package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.StockTake;

@Repository("stockTakeRepository")
public interface StockTakeRepository extends CrudRepository<StockTake, Long> {

	// @Query("select max(s.stockTakeDate) from StockTake s")
	// LocalDate getLatestDate();
	//
	// @Query("select new ph.txtdis.util.TransactionStamp(s.id, s.cutoffBy,
	// s.cutoffOn) from StockTakeReconciliation s "
	// + "where s.id = (select min(st.id) from StockTakeReconciliation st where
	// st.id > :date)")
	// TransactionStamp getCutoffStampOfStockTakeAfter(@Param("date") LocalDate
	// date);
	//
	// @Query("select min(p.id) from Picking p where p.pickDate > :date")
	// Integer getOnePickingIdAfter(@Param("date") LocalDate date);
	//
	// @Query("select min(r.id) from Receiving r where r.orderDate > :date")
	// Integer getOneReceivingIdAfter(@Param("date") LocalDate date);
	//
	// @Query("select max(s.id) from StockTakeReconciliation s where s.closedBy
	// = null and s.id < :date")
	// LocalDate getOpenStockTakeBefore(@Param("date") LocalDate date);
	//
	// @Query("select new ph.txtdis.util.TransactionStamp(s.id, s.closedBy,
	// s.closedOn) from StockTakeReconciliation s "
	// + "where s.id = :date")
	// TransactionStamp getClosureStamp(@Param("date") LocalDate date);
	//
	// @Query("select max(s.cutoffOn) from StockTakeReconciliation s")
	// ZonedDateTime getLatestCutoffDate();
}
