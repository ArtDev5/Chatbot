package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.Climate;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;
import org.springframework.stereotype.Component;

@Component
public class ClimateDateAndLocationQuestionAndAnswer implements Question {

    private final Climate climate;

    public ClimateDateAndLocationQuestionAndAnswer(Climate climate){
        this.climate = climate;
    }

    @Override
    public boolean verifyIntent(Message message) {
        return message.getIntent().equals("ClimateSpecific - DateAndLocation");
    }

    @Override
    public String getAnswer(Message message) {

        String userMessage = message.getUserMessage();

        String userDate = userMessage.substring(0, 10);
        String userLocation = userMessage.substring(10);
        String dataRegex = "^\\d{2}\\/\\d{2}\\/\\d{4}$";

        if (userDate.matches(dataRegex)) {
            return createAnswer(userDate, userLocation);

        }
        return "Informe uma data válida (dd/mm/yyyy)";
    }

    private String createAnswer(String userDate, String userLocation){



        ResponseClimate responseClimate = climate.getClimate(userDate, userLocation);

        if (responseClimate.getMax() == 0 && responseClimate.getMin() == 0) {
            return "Informe uma data dentro do limite, sendo do dia atual até 10 dias.";
        }

        return "No dia " + userDate + " está/estará batendo " + responseClimate.getTemp() + "ºC na cidade " +
                responseClimate.getCityName() + " com a condição '" + responseClimate.getCondition() +
                "', além da temperatura máxima de " + responseClimate.getMax() + " e mínima " +
                "de " + responseClimate.getMin();
    }
}
