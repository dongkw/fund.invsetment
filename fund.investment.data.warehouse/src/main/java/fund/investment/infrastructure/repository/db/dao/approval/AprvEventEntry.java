package fund.investment.infrastructure.repository.db.dao.approval;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aprv_event")
public class AprvEventEntry{

	@Id
	@Column(name = "id", nullable = false, unique = true, length = 100)
	private String id;

	@Column(name = "instruction_id", nullable = true, length = 100)
	private String instructionId;
	
	@Column(name = "status", nullable = true, length = 10)
	private Integer status;

	@Column(name = "user_id", nullable = true, length = 100)
	private String userId;

	@Column(name = "operator_id", nullable = true, length = 100)
	private String operatorId;
	
	@Column(name = "creat_time", nullable = true, length = 50)
	private LocalDateTime creatTime;
	
	@Column(name = "update_time", nullable = true, length = 50)
	private LocalDateTime updateTime;
	
}
