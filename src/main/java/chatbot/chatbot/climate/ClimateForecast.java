package chatbot.chatbot.climate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ClimateForecast {
    private int max;
    private int min;
    private String date;
    private String condition;
}
