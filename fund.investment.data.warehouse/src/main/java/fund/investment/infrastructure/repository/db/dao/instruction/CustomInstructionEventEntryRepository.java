package fund.investment.infrastructure.repository.db.dao.instruction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomInstructionEventEntryRepository extends JpaRepository<InstructionEventEntry, String>{

}
