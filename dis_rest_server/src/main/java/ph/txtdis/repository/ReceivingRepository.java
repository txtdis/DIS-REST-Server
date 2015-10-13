package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Receipt;

@Repository("receivingRepository")
public interface ReceivingRepository extends CrudRepository<Receipt, Long> {

	// @Query("select r.orderDate from Receiving r "
	// + "where r.reference = ph.txtdis.type.ReceivingReferenceType.PO and
	// r.referenceId = :id")
	// LocalDate getDateFromPurchaseOrder(@Param("id") Long id);
}
