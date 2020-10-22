package fund.investment.infrastructure.instruction.domain.model.vo;

import fund.investment.infrastructure.instruction.domain.model.enumeration.OrderStatus;
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

    private Long quantity;

    private Long fillQuantity;

    List<Fill> fills = new LinkedList<>();
}
