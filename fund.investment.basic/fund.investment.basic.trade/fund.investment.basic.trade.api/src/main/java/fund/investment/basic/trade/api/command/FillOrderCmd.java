package fund.investment.basic.trade.api.command;

import fund.investment.basic.trade.api.valueobject.Fill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FillOrderCmd extends CreateOrderCmd {

    private Fill fill;

}
