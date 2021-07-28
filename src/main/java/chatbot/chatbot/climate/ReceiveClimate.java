package chatbot.chatbot.climate;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReceiveClimate {
    @JsonProperty("temp")
    private int temperature;
    @JsonProperty("city_name")
    private String cityName;
    private List<ClimateForecast> forecast;

    public int getTemperature() {
        return temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public List<ClimateForecast> getForecast() {
        return forecast;
    }

    @Override
    public String toString() {
        return "ReceiveClimate{" +
                "temperature=" + temperature +
                ", cityName='" + cityName + '\'' +
                ", forecast=" + forecast +
                '}';
    }
}
