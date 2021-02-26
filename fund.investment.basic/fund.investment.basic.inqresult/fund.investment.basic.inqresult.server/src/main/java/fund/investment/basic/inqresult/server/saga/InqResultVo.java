package fund.investment.basic.inqresult.server.saga;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InqResultVo {
    private String unitId;
    private String skId;
    private String skInquiry;
    private BigDecimal amount;
    private String lastmodifiedId;
    private Long requestId;
    private Date lastmodifiedTime;
    private String chMemo;
    private String userId;

}
