package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Customer;
import ph.txtdis.type.CustomerType;

@Repository("customerRepository")
public interface CustomerRepository extends NameListRepository<Customer>, SpunRepository<Customer, Long> {

	List<Customer> findByDeactivatedOnNullAndTypeOrderByNameAsc(@Param("type") CustomerType bank);

	List<Customer> findByNameContaining(@Param("name") String name);
}
