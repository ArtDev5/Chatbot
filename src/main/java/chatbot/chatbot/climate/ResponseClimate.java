package chatbot.chatbot.climate;

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

    public String getCityName() {
        return cityName;
    }

    public int getTemp() {
        return temp;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public String getCondition() {
        return condition;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ResponseClimate{" +
                "cityName='" + cityName + '\'' +
                ", temp=" + temp +
                ", max=" + max +
                ", min=" + min +
                ", condition='" + condition + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
