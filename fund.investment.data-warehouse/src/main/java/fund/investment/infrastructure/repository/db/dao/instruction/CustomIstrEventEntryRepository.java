package fund.investment.infrastructure.repository.db.dao.instruction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomIstrEventEntryRepository extends JpaRepository<IstrEventEntry, String>{

}
