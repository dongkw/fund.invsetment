package fund.investment.infrastructure.common;

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
	
}
