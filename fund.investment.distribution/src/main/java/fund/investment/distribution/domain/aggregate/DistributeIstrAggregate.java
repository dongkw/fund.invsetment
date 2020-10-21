package fund.investment.distribution.domain.aggregate;

import fund.investment.infrastructure.distribution.domain.command.DistIstrRejectedCmd;
import fund.investment.infrastructure.distribution.domain.command.DistributedIstrInitCmd;
import fund.investment.infrastructure.distribution.domain.command.DistributedIstrPassCmd;
import fund.investment.infrastructure.distribution.domain.event.DistIstrInitilazationEvt;
import fund.investment.infrastructure.distribution.domain.event.DistIstrRejectedEvt;
import fund.investment.infrastructure.distribution.domain.event.DistributedIstrEvt;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Aggregate
@NoArgsConstructor
public class DistributeIstrAggregate {
	@AggregateIdentifier
	@ApiModelProperty(value = "分发id")
	private String id;
	@ApiModelProperty(value = "指令id")
	private String instructionId;
	private String userId;
	private String operatorId;
	
	@CommandHandler
	public DistributeIstrAggregate(DistributedIstrInitCmd cmd) {
		log.info(cmd.toString());
		AggregateLifecycle.apply(
				DistIstrInitilazationEvt.builder().id(cmd.getId()).instructionId(cmd.getInstructionId()).userId(cmd.getUserId()).operatorId(cmd.getOperatorId()).build());
	}
	
	@CommandHandler
	public void on(DistributedIstrPassCmd cmd) {
		log.info(cmd.toString());
		AggregateLifecycle.apply(
				DistributedIstrEvt.builder().id(cmd.getId()).instructionId(cmd.getInstructionId()).userId(cmd.getUserId()).operatorId(cmd.getOperatorId()).build());
	}
	
	@CommandHandler
	public void on(DistIstrRejectedCmd cmd) {
		log.info(cmd.toString());
		AggregateLifecycle.apply(
				DistIstrRejectedEvt.builder().id(cmd.getId()).instructionId(cmd.getInstructionId()).userId(cmd.getUserId()).operatorId(cmd.getOperatorId()).build());
	}
	
	@EventSourcingHandler
	public void action(DistIstrInitilazationEvt evt) {
		this.id = evt.getId();
		this.instructionId = evt.getInstructionId();
		this.userId = evt.getUserId();
		this.operatorId = evt.getOperatorId();
		
	}
	
	@EventSourcingHandler
	public void action(DistributedIstrEvt evt) {
		this.id = evt.getId();
		this.instructionId = evt.getInstructionId();
		this.userId = evt.getUserId();
		this.operatorId = evt.getOperatorId();
		
	}
	
	@EventSourcingHandler
	public void action(DistIstrRejectedEvt evt) {
		this.id = evt.getId();
		this.instructionId = evt.getInstructionId();
		this.userId = evt.getUserId();
		this.operatorId = evt.getOperatorId();
		
	}
}
