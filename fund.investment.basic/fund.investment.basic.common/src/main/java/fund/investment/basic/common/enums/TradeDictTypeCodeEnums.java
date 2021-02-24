package fund.investment.basic.common.enums;

import com.qunji.common.enums.BaseEnums;

/**
 * 交易管理枚举代码类
 */
public enum TradeDictTypeCodeEnums implements BaseEnums {
    TRADE_INS_FLOW("TRADE_INS_FLOW", "流程操作类型"),
    TRADE_INS_FLOW_STATUS("TRADE_INS_FLOW_STATUS", "流程操作状态"),
    TRADE_COMM_SEND_STATUS("TRADE_COMM_SEND_STATUS", "数据状态"),
    TRADE_INNER_TRADE("TRADE_INNER_TRADE", "是否内部交易"),
    TRADE_INS_MATCH("TRADE_INS_MATCH", "配对标志"),
    TRADE_CLEAR_SPEED("TRADE_CLEAR_SPEED", "清算速度"),
    TRADE_INS_STATUS("TRADE_INS_STATUS", "投资指令状态"),
    TRADE_RESALE_FLOW_STATUS("TRADE_RESALE_FLOW_STATUS", "债券回售流程状态"),
    TRADE_INSTR_SOURCE("TRADE_INSTR_SOURCE", "指令来源"),
    TRADE_RESALE_STATUS("TRADE_RESALE_STATUS", "债券回售状态"),
    TRADE_NEW_BOND_PURCHASE_FLOW("TRADE_NEW_BOND_PURCHASE_FLOW", "可转债流程状态"),
    TRADE_NEW_BOND_TRANSFER_NOTE("TRADE_NEW_BOND_TRANSFER_NOTE", "新债划款指令备注类型"),
    TRADE_ENTRUST_STATUS("TRADE_ENTRUST_STATUS", "委托状态"),
    TRADE_ENTRUST_FROZEN_TYPE("TRADE_ENTRUST_FROZEN_TYPE", "委托冻结方式"),
    TRADE_ENTRUST_TYPE("TRADE_ENTRUST_TYPE", "委托方式"),
    TRADE_ENTRUST_SOURCE("TRADE_ENTRUST_SOURCE", "委托来源"),
    TRADE_OFFER_DIRECTION("TRADE_OFFER_DIRECTION", "报价方向"),
    TRADE_CANCEL_FLAG("TRADE_CANCEL_FLAG", "撤单标志"),
    TRADE_BALANCE_TYPE("TRADE_BALANCE_TYPE", "结算方式"),
    TRADE_OFFER_STATIS("TRADE_OFFER_STATIS", "报价状态"),
    TRADE_OPERATE_TYPE("TRADE_OPERATE_TYPE", "操作类别"),
    TRADE_INQUIRE_RESULT_STATUS("TRADE_INQUIRE_RESULT_STATUS", "询价结果状态"),
    TRADE_SAVINGS_STATUS("TRADE_SAVINGS_STATUS","存单状态"),
    BASE_INT_TYPE("BASE_INT_TYPE","计息类型"),
    BASE_BOND_TRADE_TYPE("BASE_BOND_TRADE_TYPE", "交易类型"),
    TRADE_INTEREST_FREQU("TRADE_INTEREST_FREQU", "付息频率"),
    TRADE_DEADLINE_TYPE("TRADE_DEADLINE_TYPE", "存款到期类型"),
    TRADE_LIMIT("TRADE_LIMIT","提前支取限制"),
    BASE_RATING_TYPE_CODE("BASE_RATING_TYPE_CODE","债券内部评级"),
    TRADE_CLEAR_TYPE("TRADE_CLEAR_TYPE","清算类型"),
	TRADE_DRAW_TYPE("TRADE_DRAW_TYPE","支取类型"),
	BASE_BOND_INT_TYPE("BASE_BOND_INT_TYPE","固定利率"),
    BASE_DISTRI_METHOD("BASE_DISTRI_METHOD","发行方式"),
    TRADE_TRANSFER_TYPE("TRADE_TRANSFER_TYPE","划款类型"),
    BASE_BOND_PAYINI_TYPE("BASE_BOND_PAYINI_TYPE","付息类型"),
    TRADE_TRANSFER_BUSINESS_TYPE("TRADE_TRANSFER_BUSINESS_TYPE","划款业务类型"),
    BASE_OUT_CRDT_RATING("BASE_OUT_CRDT_RATING","评级级别-其它信用等级"),
    TRADE_INSTR_ISSUED_SOURCE("TRADE_INSTR_ISSUED_SOURCE","指令下达来源"),
    FOREIGN_INSTRUCTSTATUS("FOREIGN_INSTRUCTSTATUS","外汇指令状态"),
    FOREIGN_TRANSFERSTATUS("FOREIGN_TRANSFERSTATUS","外汇划款状态"),
    FOREIGN_ISARRIVE("FOREIGN_ISARRIVE","外汇到账状态"),
    FOREIGN_REVIEWSTATUS("FOREIGN_REVIEWSTATUS","外汇复核状态"),
    FOREIGN_ACTIONTYPE("FOREIGN_ACTIONTYPE","外汇操作状态"),
    FOREIGN_AUDITSTATUS("FOREIGN_AUDITSTATUS","外汇审核状态"),
    TRADE_TRAN_DIRECT("TRADE_TRAN_DIRECT","外汇划款方向"),
    FOREIGN_BUSINESSCODE("FOREIGN_BUSINESSCODE","外汇业务类型"),
    FOREIGN_MARKET("FOREIGN_MARKET","外汇市场"),
   ;

    private String code;
    private String name;

    TradeDictTypeCodeEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TradeDictTypeCodeEnums getEnumByCode(String code) {
        for (TradeDictTypeCodeEnums enums : TradeDictTypeCodeEnums.values()) {
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
