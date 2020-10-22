package fund.investment.infrastructure.repository.db.dao.trade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomTradeEventEntryRepository extends JpaRepository<TradeEventEntry, String>{

}