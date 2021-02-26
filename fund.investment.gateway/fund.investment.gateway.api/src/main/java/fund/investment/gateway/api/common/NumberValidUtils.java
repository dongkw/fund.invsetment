package fund.investment.gateway.api.common;

import java.math.BigDecimal;

public class NumberValidUtils {

    /**
     * 判断两个数是否相等
     *
     * @param va1
     * @param val2
     * @return
     */
    public static Boolean equalDecimal(BigDecimal va1, BigDecimal val2) {
        if (va1 != null) {
            if (val2 != null) {
                if (va1.setScale(8, BigDecimal.ROUND_DOWN)
                        .compareTo((val2.setScale(8, BigDecimal.ROUND_DOWN))) == 0) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            } else {
                return Boolean.FALSE;
            }
        } else {
            if (val2 != null) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        }
    }

    public static boolean equalLong(Long va1, Long val2) {
        if (va1 != null) {
            if (val2 != null) {
                if (va1 - val2 == 0L) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            } else {
                return Boolean.FALSE;
            }
        } else {
            if (val2 != null) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        }
    }
}
