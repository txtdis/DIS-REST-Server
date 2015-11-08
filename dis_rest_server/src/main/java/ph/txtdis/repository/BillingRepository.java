package ph.txtdis.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Customer;

@Repository("billingRepository")
public interface BillingRepository extends CrudRepository<Billing, Long> {

	List<Billing> findByFullyPaidFalseAndCustomerAndUnpaidValueGreaterThanOrderByOrderDateDesc(
			@Param("customer") Customer c, @Param("balance") BigDecimal b);

	List<Billing> findByFullyPaidFalseAndUnpaidValueGreaterThanOrderByCustomerAscOrderDateDesc(
			@Param("balance") BigDecimal b);

	List<Billing> findByNumIdGreaterThanAndOrderDateBetweenOrderByOrderDateAscPrefixAscNumIdAscSuffixAsc(
			@Param("idNo") Long id, @Param("startOfDay") LocalDate s, @Param("endOfDay") LocalDate e);

	List<Billing> findByOrderDate(@Param("date") LocalDate d);

	Billing findByPrefixAndSuffixAndNumId(@Param("prefix") String p, @Param("suffix") String s, @Param("id") Long id);

	// S/O been referenced
	Billing findFirstByBookingId(@Param("id") Long id);

	// next S/O
	Billing findFirstByBookingIdNotNullAndBookingIdGreaterThanOrderByBookingIdAsc(@Param("id") Long id);

	// previous S/O
	Billing findFirstByBookingIdNotNullAndBookingIdLessThanOrderByBookingIdDesc(@Param("id") Long id);

	// first S/O of the day
	Billing findFirstByBookingIdNotNullAndCreatedOnBetweenOrderByCreatedOnAsc(@Param("startOfDay") ZonedDateTime s,
			@Param("endOfDay") ZonedDateTime e);

	// first S/O
	Billing findFirstByBookingIdNotNullOrderByBookingIdAsc();

	// last S/O
	Billing findFirstByBookingIdNotNullOrderByBookingIdDesc();

	// first S/I of the day
	Billing findFirstByNumIdNotNullAndNumIdGreaterThanAndCreatedOnBetweenOrderByCreatedOnAsc(@Param("zero") Long z,
			@Param("startOfDay") ZonedDateTime s, @Param("endOfDay") ZonedDateTime e);

	// next S/I (positive numId)
	Billing findFirstByNumIdNotNullAndNumIdGreaterThanAndIdGreaterThanOrderByIdAsc(@Param("zero") Long z,
			@Param("id") Long i);

	// previous S/I (positive numId)
	Billing findFirstByNumIdNotNullAndNumIdGreaterThanAndIdLessThanOrderByIdDesc(@Param("zero") Long z,
			@Param("id") Long i);

	// first S/I (positive numId)
	Billing findFirstByNumIdNotNullAndNumIdGreaterThanOrderByIdAsc(@Param("zero") Long i);

	// first S/I (last numId)
	Billing findFirstByNumIdNotNullAndNumIdGreaterThanOrderByIdDesc(@Param("zero") Long i);

	// previous D/R (negative numId)
	Billing findFirstByNumIdNotNullAndNumIdGreaterThanOrderByNumIdAsc(@Param("id") Long i);

	// first D/R of the day
	Billing findFirstByNumIdNotNullAndNumIdLessThanAndCreatedOnBetweenOrderByCreatedOnAsc(@Param("zero") Long z,
			@Param("startOfDay") ZonedDateTime s, @Param("endOfDay") ZonedDateTime e);

	// last D/R (negative numId)
	Billing findFirstByNumIdNotNullAndNumIdLessThanOrderByNumIdAsc(@Param("zero") Long z);

	// first & next D/R (negative numId)
	Billing findFirstByNumIdNotNullAndNumIdLessThanOrderByNumIdDesc(@Param("id") Long i);

	Billing findFirstByPrefixAndSuffixAndNumIdBetweenOrderByNumIdDesc(@Param("prefix") String p,
			@Param("suffix") String s, @Param("first") Long f, @Param("last") Long l);
}
