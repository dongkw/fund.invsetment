package fund.investment.infrastructure.repository.db.dao.approval;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomAprovalEventEntryRepository extends JpaRepository<ApprovalEventEntry, String>{

}
