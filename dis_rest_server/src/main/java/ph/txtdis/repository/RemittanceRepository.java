package ph.txtdis.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Remittance;

@Repository("remittanceRepository")
public interface RemittanceRepository extends SpunRepository<Remittance, Long> {

	List<Remittance> findByCreatedOnBetweenOrderByIdAsc(@Param("start") ZonedDateTime s, @Param("end") ZonedDateTime e);

	Remittance findByDraweeBankAndCheckId(@Param("bank") Customer b, @Param("checkId") Long id);

	Remittance findFirstByPaymentDateOrderByIdAsc(@Param("date") LocalDate d);
}
