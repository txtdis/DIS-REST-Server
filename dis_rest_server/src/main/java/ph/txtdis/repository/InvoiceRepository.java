package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Booking;
import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Invoice;

@Repository("invoiceRepository")
public interface InvoiceRepository extends SpunRepository<Invoice, Long> {

	Invoice findByBooking(@Param("booking") Booking booking);

	List<Invoice> findByCustomerAndFullyPaidOrderByOrderDateDesc(@Param("customer") Customer customer,
			@Param("fullyPaid") boolean fullyPaid);

	List<Invoice> findByFullyPaidOrderByCustomerAscOrderDateDesc(@Param("fullyPaid") boolean fullyPaid);

	List<Invoice> findByOrderDateBetweenOrderByOrderDateAsc(@Param("start") LocalDate start,
			@Param("end") LocalDate end);

	Invoice findByPrefixAndNumIdAndSuffix(@Param("prefix") String prefix, @Param("id") Long id,
			@Param("suffix") String suffix);

	Invoice findFirstByPrefixAndSuffixAndNumIdBetweenOrderByNumIdDesc(@Param("prefix") String prefix,
			@Param("suffix") String suffix, @Param("startId") Long startId, @Param("endId") Long endId);
}
