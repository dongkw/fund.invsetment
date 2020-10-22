package fund.investment.infrastructure.common;

import java.io.Serializable;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DomainEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6857309372784084346L;
	private String id;
	
	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
}
