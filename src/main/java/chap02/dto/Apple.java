package chap02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Apple {
    private int weight;
    private Color color;

    public enum Color {
        RED, GREEN
    }
}
