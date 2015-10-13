package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.dto.ReceivingSummary;

@Repository("receivingSummaryRepository")
public interface ReceivingSummaryRepository extends CrudRepository<ReceivingSummary, Long> {

	List<ReceivingSummary> findByOrderDateBetween(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
}
