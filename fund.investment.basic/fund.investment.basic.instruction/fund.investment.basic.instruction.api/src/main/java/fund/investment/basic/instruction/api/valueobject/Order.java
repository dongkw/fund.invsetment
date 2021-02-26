package fund.investment.basic.instruction.api.valueobject;

import fund.investment.basic.instruction.api.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;

    private OrderStatus status;

    private long quantity;

    private long fillQuantity;

    List<Fill> fills = new LinkedList<>();
}
