package fund.investment.instruction.domain.model.aggregate;

import com.google.gson.Gson;
import fund.investment.infrastructure.common.DomainAggregate;
import fund.investment.infrastructure.instruction.domain.model.command.CreateConfirmIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.command.CreateFailIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.command.*;
import fund.investment.infrastructure.instruction.domain.model.enumeration.ApprovalStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.DistributeStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.event.*;
import fund.investment.instruction.domain.model.aggregate.status.*;
import fund.investment.instruction.domain.model.entity.IstrTradeElement;
import fund.investment.instruction.domain.model.entity.OrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;

import java.util.Objects;

@Data
@Slf4j
//@Aggregate
@ApiModel("指令")
@NoArgsConstructor
@AllArgsConstructor
public class InstructionAggregate extends DomainAggregate {
	
	@ApiModelProperty(value = "指令编号")
	@AggregateIdentifier
    private String id;
	
	@ApiModelProperty(value = "指令状态 ")
    private InstructionState instructionState;

	@ApiModelProperty(value = "异常编码")
	private String errorCode;

	@ApiModelProperty(value = "异常描述")
	private String errorMsg;
	
	@ApiModelProperty(value = "成交单元id")
	private String unitId;
	
	@ApiModelProperty(value = "账号id")
	private String accountId;
	
	@ApiModelProperty(value = "用户id")
	private String userId;
	
	@ApiModelProperty(value = "用户id")
	private TradeType tradeType;
	
	@AggregateMember
	private IstrTradeElement istrTradeElement;
	
	@ApiModelProperty(value = "审批状态")
    private ApprovalStatus approvalStatus;

    @ApiModelProperty(value = "分发状态 ")
    private DistributeStatus distributeStatus;
    
	@AggregateMember
    private OrderDetail orderDetail;
    
//	@AggregateMember
//    private FillDetail fillDetail;
    
//    @CommandHandler
//    public InstructionAggregate(CreateIstrCmd cmd) {
//    	log.info(String.format("Create Instruction: %s", new Gson().toJson(cmd)));
//    }

	/**
	 * 接收指令创建确认命令
	 * @param cmd
	 */
	@CommandHandler
	public void handle(CreateConfirmIstrCmd cmd) {
		log.info("Receive command: {}", cmd);
		//获取指令状态执行创建确认命令
		getInstructionState().createConfirm(cmd);
	}

	/**
	 * 接收指令创建失败命令
	 * @param cmd
	 */
    @CommandHandler
    public void handle(CreateFailIstrCmd cmd) {
		log.info("Receive command: {}", cmd);
		getInstructionState().createFail(cmd);
    }

	/**
	 * 取消确认指令命令
	 * @param cmd
	 */
	@CommandHandler
    public void handle(CancelConfIstrCmd cmd) {
		log.info("Receive command: {}", cmd);
		getInstructionState().cancelConfirm(cmd);

    }

	/**
	 * 【审核通过命令】
	 * @param cmd
	 */
	@CommandHandler
	public void handle(AprvPassIstrCmd cmd) {
		log.info("Receive command: {}", cmd);
		getInstructionState().aprvPass(cmd);
	}

	/**
	 * 【分发通过命令】
	 * @param cmd
	 */
	@CommandHandler
	public void handle(DistributeIstrCmd cmd) {
		log.info("Receive command: {}", cmd);
		getInstructionState().distribute(cmd);
	}
    
    
    @CommandHandler
    public void handle(ReceiveIstrFillCmd cmd) {
		log.info("Receive command: {}", cmd);
		getInstructionState().receiveFill(this, cmd);
    }

    
    //event sourcing
    @EventSourcingHandler
    public void handle(IstrCancellingEvt evt) {
//    	this.id = evt.getId();
    	this.instructionState = new CancellingInstructionState();
		log.info(String.format("[instruction] Cancel Instruction event: %s", new Gson().toJson(evt)));
    }
    
    @EventSourcingHandler
    public void handle(IstrConfirmedEvt evt) {
		log.info("Recieved Event: {}", evt);
//    	this.id = evt.getId();
    	this.instructionState = new ConfirmedInstructionState();
    }

	/**
	 * 处理指令审核通过事件
	 * @param evt
	 */
	@EventSourcingHandler
    public void handle(IstrPassedEvt evt) {
		log.info("Recieved Event: {}", evt);
//    	this.id = evt.getId();
    	this.instructionState = new PassedInstructionState();
    }

	/**
	 * 处理指令分发成功命令
	 * @param evt
	 */
	@EventSourcingHandler
    public void handle(IstrPendingEvt evt) {
		log.info("Recieved Event: {}", evt);
//    	this.id = evt.getId();
    	this.instructionState = new PendingInstructionState();
    }
    
    @EventSourcingHandler
    public void handle(IstrExecutingEvt evt) {
		log.info("Recieved Event: {}", evt);
//    	this.id = evt.getId();
    }

	/**
	 * 【指令审核通过】事件处理
	 * @param evt
	 */
	@EventSourcingHandler
    public void handle(IstrFailedEvt evt) {
		log.info("Recieved Event: {}", evt);
//    	this.id = evt.getId();
    	this.instructionState = new FailedInstructionState();
    }

	/**
	 * 【分发通过】事件处理
	 * @param evt
	 */
	@EventSourcingHandler
	public void handle(IstrCompletedEvt evt) {
		log.info("Recieved Event: {}", evt);
//		this.id = evt.getId();
		this.instructionState = new CompletedInstructionState();
	}

	/**
	 * 处理指令取消事件
	 * @param evt
	 */
	@EventSourcingHandler
	public void handle(IstrCancelledEvt evt) {
		log.info("Recieved Event: {}", evt);
//		this.id = evt.getId();
		this.instructionState = new CancelledInstructionState();
	}

	/**
	 * 【委托创建】事件处理
	 * @param evt
	 */
	@EventHandler
	public void handle(IstrOrderCreatedEvt evt) {
		log.info("Recieved Event: {}", evt);
//		this.id = evt.getId();
		//TODO 添加委托赋值操作
		this.orderDetail = new OrderDetail();
		this.instructionState = new ExcutingInstructionState();
	}

	/**
	 * 处理 委托取消事件
	 * @param evt
	 */
	@EventSourcingHandler
	public void handle(IstrOrderCancelledEvt evt) {
		log.info("Recieved Event: {}", evt);
//		this.id = evt.getId();
		if(Objects.isNull(this.orderDetail)){
			this.orderDetail = new OrderDetail();
		}
		boolean flag = this.orderDetail.cancelOrder(evt);
		if(flag){
			this.instructionState = new PendingInstructionState();
			log.info("Order cancelled all, state change to pending: {}", evt);
		}
	}

	/**
	 * 【委托创建】事件处理
	 * @param evt
	 */
	@EventSourcingHandler
	public void handle(IstrFillReceivedEvt evt) {
		log.info("Recieved Event: {}", evt);
//		this.id = evt.getId();
		this.instructionState = new ExcutingInstructionState();
	}

}
