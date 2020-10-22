package fund.investment.infrastructure.common;

import java.io.Serializable;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Data;

@Data
public class DomainCommand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1018705596034571430L;
	
	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
