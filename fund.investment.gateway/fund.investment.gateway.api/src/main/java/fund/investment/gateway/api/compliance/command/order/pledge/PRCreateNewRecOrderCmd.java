package fund.investment.gateway.api.compliance.command.order.pledge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PRCreateNewRecOrderCmd extends PRPlacingOrderCmd {

    private String isMatch;

}
