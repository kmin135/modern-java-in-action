package common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Dish {
    private String name;
    private int calories;
    private TYPE type;


    public enum TYPE {
        FISH, MEAT, OTHER
    }
}
