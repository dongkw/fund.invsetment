package fund.investment.infrastructure.repository.db.dao.distribution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomDistEventEntryRepository extends JpaRepository<DistEventEntry, String>{

}
