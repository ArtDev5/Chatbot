package chatbot.chatbot.climate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class ClimateServices {

    @Value("${urlClimate}")
    private String urlClimate;

    private final RestTemplate restTemplate;

    public ClimateServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseClimate getClimate(){

        String url = urlClimate+"Porto Real do Colégio";

        ReceiveClimate receiveClimate = restTemplate.getForObject(url, ReceiveClimate.class);

        ResponseClimate climate = new ResponseClimate();
        climate.getWeatherData(receiveClimate);

        return climate;
    }

    public ResponseClimate getClimate(String userDate, String userLocation){

            String url = urlClimate+userLocation;

            ReceiveClimate receiveClimate = restTemplate.getForObject(url, ReceiveClimate.class);

            ResponseClimate climate = new ResponseClimate();
            climate.getWeatherData(receiveClimate, userDate);

            return climate;
    }

}
