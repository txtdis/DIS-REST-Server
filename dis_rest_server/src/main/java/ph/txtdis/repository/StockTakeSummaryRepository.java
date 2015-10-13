package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.dto.StockTakeSummary;

@Repository("stockTakeSummaryRepository")
public interface StockTakeSummaryRepository extends CrudRepository<StockTakeSummary, Long> {

	List<StockTakeSummary> findByStockTakeDate(@Param("date") LocalDate date);
}
