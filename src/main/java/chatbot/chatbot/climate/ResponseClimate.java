package chatbot.chatbot.climate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseClimate {
    private String cityName;
    private int temp;
    private int max;
    private int min;
    private String condition;
    private String date;

    public ResponseClimate(ReceiveClimate clima){
        cityName = clima.getCityName();
        temp = clima.getTemperature();
        clima.getForecast().forEach(forecast -> {
            max = forecast.getMax();
            min = forecast.getMin();
            condition = forecast.getCondition();
        });
    }

    public ResponseClimate(ReceiveClimate clima, String userDate){
        cityName = clima.getCityName();
        temp = clima.getTemperature();
        clima.getForecast().forEach(forecast -> {
            date = forecast.getDate();
            if(date.equals(userDate.substring(0,5))){
                max = forecast.getMax();
                min = forecast.getMin();
                condition = forecast.getCondition();
            }
        });
    }
}
