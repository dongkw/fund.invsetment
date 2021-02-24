package fund.investment.basic.common.enums;

/**
 * 交易表名
 */
public enum FlowTradeTableNameEnums {
    INQUIRY("TRADE_BANK_INQUIRY", "银行间询价信息表"),
    INQRESULT("TRADE_INQUIRY_RESULT", "询价结果表"),
    FUNDSINSTR("TRADE_FUNDS_INSTR_INFO", "资金指令表"),
    ZYSHG("TRADE_INVEST_BANK_ZYSHG", "投资指令子表-银行间质押式回购指令表"),
    INSTRUCTION("TRADE_INVEST", "投资指令表"),
    ENTRUST("TRADE_ENTRUST", "委托表"),
    ENTRUST_BOND_NO_MATCH("TRADE_ENTRUST_BOND_NO_MATCH", "暂时未匹配的委托表质押券"),
    TRADEDEALSUM("TRADE_DEAL_SUM", "成交回报表-实时汇总"),
    TRADEDEALDETAIL("TRADE_DEAL_DETAIL", "成交明细表"),
    ;


    private String code;
    private String name;

    private FlowTradeTableNameEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static FlowTradeTableNameEnums getEnumByCode(String code) {
        FlowTradeTableNameEnums[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            FlowTradeTableNameEnums enums = var1[var3];
            if (enums.code.equalsIgnoreCase(code)) {
                return enums;
            }
        }

        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
