package fund.investment.app.pledge.repo.server.utils;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

@Slf4j
public class RequestHandleUtils {

    public static <T> ResponseEntity<T> execute(Supplier<ResponseEntity<T>> executor) {
        try {
            return executor.get();
        } catch (IllegalArgumentException illE) {
            log.warn("请求超时出错，返回失败", illE);
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        } catch (CommandExecutionException cmdE) {
            log.warn("请求运行出错，返回失败", cmdE);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            log.warn("请求异常出错，返回失败", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
