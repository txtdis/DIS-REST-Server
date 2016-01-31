package ph.txtdis.remote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("remoteRepository")
public interface RemoteRepository extends CrudRepository<Remote, Long> {
}
