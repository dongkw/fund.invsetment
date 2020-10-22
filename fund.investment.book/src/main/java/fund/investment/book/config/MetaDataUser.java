package fund.investment.book.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaDataUser implements MetaDataUserInterface {

    private String name;

    private Long userId;

    private Long customerId;
}