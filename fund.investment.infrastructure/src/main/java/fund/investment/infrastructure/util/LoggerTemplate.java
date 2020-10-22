package fund.investment.infrastructure.util;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoggerTemplate {

    private Object content;
    private String name;

    public String toJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
