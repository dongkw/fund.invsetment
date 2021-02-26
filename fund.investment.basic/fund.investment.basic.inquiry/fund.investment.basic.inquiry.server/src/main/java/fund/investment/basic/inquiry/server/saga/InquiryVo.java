package fund.investment.basic.inquiry.server.saga;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InquiryVo {

    private Long requestId;
    private String unitId;
    private String inquiryId;
    private BigDecimal amount;
    private String lastmodifiedId;
    private String userId;
    private Date lastmodifiedTime;

    private String skId;
    private String chSourceNo;

    private String chMemo;
}
