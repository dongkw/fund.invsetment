package fund.investment.basic.instruction.server.service.saga;


import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.server.aggregate.status.InstructionState;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author dongkw
 * @Date 2020/9/21、9:35 上午
 **/
@Data
public class IstrVo {

    private Long requestId;

    private String istrId;

    private String skId;

    private InstructionState state;

    private String securityCode;

    private String unitId;

    private BigDecimal amount;

    private TradeType tradeType;

    private String lastmodifiedId;

    private Date lastmodifiedTime;

    private String inquiryResultId;

    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    private String userId;

}
