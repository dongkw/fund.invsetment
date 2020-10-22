package fund.investment.infrastructure.common;

<<<<<<< HEAD
import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomainAggregate {

    @AggregateIdentifier
    private String id;

    @Override
    public String toString() {
        return LoggerTemplate.builder()
                             .content(this)
                             .name(this.getClass().getSimpleName())
                             .build()
                             .toJson();
    }
=======
import java.io.Serializable;

import fund.investment.infrastructure.util.LoggerTemplate;

public class DomainAggregate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 634315798349252244L;
	
	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
>>>>>>> 3a0a2561e0fa4da06e959b868de6b818fed652af
}
