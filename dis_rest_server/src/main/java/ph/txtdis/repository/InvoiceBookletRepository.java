package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.InvoiceBooklet;

@Repository("invoiceBookletRepository")
public interface InvoiceBookletRepository extends CrudRepository<InvoiceBooklet, Long> {

	List<InvoiceBooklet> findByOrderByPrefixAscStartIdAscSuffixAsc();

	InvoiceBooklet findByPrefixAndSuffixAndStartIdLessThanEqualAndEndIdGreaterThanEqual(
	// @formatter:off
		@Param("prefix") String idPrefix,
		@Param("suffix") String idSuffix,
		@Param("firstId") Long firstId,
		@Param("lastId") Long lastId);
	// @formatter:on
}
