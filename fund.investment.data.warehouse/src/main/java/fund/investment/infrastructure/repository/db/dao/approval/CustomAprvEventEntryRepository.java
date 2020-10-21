package fund.investment.infrastructure.repository.db.dao.approval;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomAprvEventEntryRepository extends JpaRepository<AprvEventEntry, String>{

}
