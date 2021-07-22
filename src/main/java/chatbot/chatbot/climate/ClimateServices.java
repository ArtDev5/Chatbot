package chatbot.chatbot.climate;

import org.springframework.web.client.RestTemplate;

public class ClimateServices {

    private final RestTemplate restTemplate;

    public ClimateServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseClimate getClimate(){

        String url = getUrl();

        ReceiveClimate receiveClimate = restTemplate.getForObject(url, ReceiveClimate.class);

        ResponseClimate climate = new ResponseClimate(receiveClimate);

        return climate;
    }

    public ResponseClimate getClimate(String userDate, String userLocation){

            String url = getUrl(userLocation);

            ReceiveClimate receiveClimate = restTemplate.getForObject(url, ReceiveClimate.class);

            ResponseClimate climate = new ResponseClimate(receiveClimate, userDate);

            return climate;
    }

    private String getUrl(){

        return "https://api.hgbrasil.com/weather?array_limit=1" +
                "&fields=only_results,temp,city_name,forecast,max,min," +
                "date,condition&key=ddbccb12&city_name=Porto Real do Colégio";
    }

    private String getUrl(String name){

        return "https://api.hgbrasil.com/weather?array_limit=20" +
                "&fields=only_results,temp,city_name,forecast,max,min," +
                "date,condition&key=ddbccb12&city_name="+name;
    }


}
