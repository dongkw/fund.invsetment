package fund.investment.infrastructure.instruction.domain.model.enumeration;

/**
 * 交易方向
 *
 * @author 芳军
 */
public enum TradeSide {

    UNDEFINED(0, "未定义"),
    BUY(1, "买入"),
    SELL(2, "卖出"),
    INVERSE_REPO(21, "现货 逆回购"),
    REPO(22, "现货 正回购"),
    PLEDG_IN(23, "质押入库"),
    PLEDG_OUT(24, "质押出库");

    TradeSide(int id, String note) {
        this.id = id;
        this.note = note;
    }

    private int    id;
    private String note;
}
