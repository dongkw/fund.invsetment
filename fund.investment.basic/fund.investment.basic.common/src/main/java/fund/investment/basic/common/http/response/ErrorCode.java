package fund.investment.basic.common.http.response;

import com.qunji.common.util.StringUtils;

import java.text.MessageFormat;

public interface ErrorCode {
    String SUCCESS = "0000";

    String getCode();

    String getMessage();

    default String getMessage(Object... strings) {
        return MessageFormat.format(this.getMessage(), strings);
    }

    default com.qunji.common.dto.ErrorCode format(Object... strings) {
        com.qunji.common.dto.ErrorCode.ErrorCodeImpl impl = new com.qunji.common.dto.ErrorCode.ErrorCodeImpl();
        impl.setCode(this.getCode());
        impl.setMessage(MessageFormat.format(this.getMessage(), strings));
        return impl;
    }

    default boolean equals(String eqCode) {
        return StringUtils.equals(this.getCode(), eqCode);
    }

    default boolean isSuccess() {
        return "0000".equals(this.getCode());
    }

    default boolean isError() {
        return !"0000".equals(this.getCode());
    }

    public static class ErrorCodeImpl implements com.qunji.common.dto.ErrorCode {
        private String code;
        private String message;

        public ErrorCodeImpl() {
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public String getMessage(Object... strings) {
            return null;
        }

        public com.qunji.common.dto.ErrorCode format(Object... strings) {
            return null;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
