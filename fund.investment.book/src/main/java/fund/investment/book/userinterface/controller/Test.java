package fund.investment.book.userinterface.controller;

import fund.investment.book.config.ContractCommandGateway;
import fund.investment.infrastructure.book.domain.model.command.order.VerfOrderCmd;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dongkw
 * @Date 2020/10/14、1:38 下午
 **/
@RestController
public class Test {

    @Autowired
    private ContractCommandGateway commandGateway;

    @PostMapping("/verf")
    public ResponseEntity handler(@RequestBody VerfOrderCmd cmd) {
        commandGateway.send(cmd);
        return ResponseEntity.ok().build();
    }

}
