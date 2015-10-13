package ph.txtdis.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Remittance;

@Repository("remittanceRepository")
public interface RemittanceRepository extends CrudRepository<Remittance, Long> {

	// @Query("select r from Remittance r join r.endorsements re join r.details
	// rd, Invoice i, "
	// + "Picking p join p.details pd where rd.invoicing = i and i.booking =
	// pd.booking and "
	// + "re.orderDate = :date and p.truck = :truck ")
	// List<Remittance> getRemittances(@Param("date") LocalDate date,
	// @Param("truck") Truck truck);
	//
	// @Query("select r.endorsements from Remittance r where r.id = :id ")
	// List<RemittanceEndorsement> getEndorsements(@Param("id") Long id);
	//
	// @Query("select r.endorsements from Remittance r join r.endorsements re
	// where r.id = :id "
	// + "and re.endorsement = :endorsement")
	// RemittanceEndorsement getEndorsement(@Param("id") Long id,
	// @Param("endorsement") EndorsementType endorsement);
	//
	// @Query("select sum(d.paymentValue) from Remittance r join r.details d
	// where d.invoicing = :invoice")
	// BigDecimal getPayment(@Param("invoice") Invoice invoice);

	List<Remittance> findByCreatedOnBetweenOrderByIdAsc(@Param("zdtStart") ZonedDateTime zdtStart,
			@Param("zdtEnd") ZonedDateTime zdtEnd);
}
