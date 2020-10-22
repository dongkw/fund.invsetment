package fund.investment.infrastructure.repository.db.dao.distribution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomDistributionEventEntryRepository extends JpaRepository<DistributionEventEntry, String>{

}
