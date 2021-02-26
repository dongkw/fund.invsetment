package fund.investment.app.pledge.repo.api.event.trade;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PRNewRecOrderCreatedEvt extends PROrderCreatedEvt {

    private String isMatch;

}
