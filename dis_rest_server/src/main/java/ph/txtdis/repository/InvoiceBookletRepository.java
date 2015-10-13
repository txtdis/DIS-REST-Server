package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.InvoiceBooklet;

@Repository("invoiceBookletRepository")
public interface InvoiceBookletRepository extends CrudRepository<InvoiceBooklet, Long> {

	// @formatter:off
	InvoiceBooklet findByIdPrefixAndIdSuffixAndStartIdLessThanEqualAndEndIdGreaterThanEqual(
			@Param("idPrefix") String idPrefix,
			@Param("idSuffix") String idSuffix,
			@Param("firstId") Long firstId,
			@Param("lastId") Long lastId);
	// @formatter:on

	List<InvoiceBooklet> findByOrderByIdPrefixAscStartIdAscIdSuffixAsc();
}
