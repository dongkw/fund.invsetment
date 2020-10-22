package fund.investment.infrastructure.common;

<<<<<<< HEAD
=======
import java.io.Serializable;

>>>>>>> 3a0a2561e0fa4da06e959b868de6b818fed652af
import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomainEvent {

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
>>>>>>> 3a0a2561e0fa4da06e959b868de6b818fed652af
}
