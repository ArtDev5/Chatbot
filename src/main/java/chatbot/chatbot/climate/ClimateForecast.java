package chatbot.chatbot.climate;

public class ClimateForecast {
    private int max;
    private int min;
    private String date;
    private String condition;

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public String getDate() {
        return date;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "ClimateForecast{" +
                "max=" + max +
                ", min=" + min +
                ", date='" + date + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
