package fund.investment.app.pledge.repo.server.service.trade.saga;

import lombok.Data;

import java.util.Date;

@Data
public class ManualMatchEvt {

    private String id;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    private String instrSkId;

    private String instrSkInstr;

    private String skCombi;


    private String instructionId;

    private String tradeType;

    private Long requestId;
}
