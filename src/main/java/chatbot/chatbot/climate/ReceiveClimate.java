package chatbot.chatbot.climate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReceiveClimate {
    @JsonProperty("temp")
    private int temperature;
    @JsonProperty("city_name")
    private String cityName;
    private List<ClimateForecast> forecast;
}
