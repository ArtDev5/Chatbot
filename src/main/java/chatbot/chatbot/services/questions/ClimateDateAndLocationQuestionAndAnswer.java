package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ClimateDateAndLocationQuestionAndAnswer implements Question {

    private final ClimateServices climateServices;

    public ClimateDateAndLocationQuestionAndAnswer(ClimateServices climateServices){
        this.climateServices = climateServices;
    }

    @Override
    public boolean verifyIntent(Message message) {
        return message.getIntent().equals("ClimateSpecific - DateAndLocation");
    }

    @Override
    public String getAnswer(Message message) {

        Date userDate = message.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String userDateFormatted = dateFormat.format(userDate);
        String userCity = message.getCity();

        return createAnswer(userDateFormatted, userCity);
    }

    private String createAnswer(String userDate, String userCity){

        ResponseClimate responseClimate = climateServices.getClimate(userDate, userCity);

        if (responseClimate.getMax() == 0 && responseClimate.getMin() == 0) {
            return "Informe uma data dentro do limite, sendo do dia atual até 10 dias.";
        }

        return "No dia " + userDate + " está/estará batendo " + responseClimate.getTemp() + "ºC na cidade " +
                responseClimate.getCityName() + " com a condição '" + responseClimate.getCondition() +
                "', além da temperatura máxima de " + responseClimate.getMax() + " e mínima " +
                "de " + responseClimate.getMin();
    }
}
