package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.dialogflow.MessageEntity;
import chatbot.chatbot.interfaces.Question;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ClimateDateAndLocationQuestionAndAnswer implements Question {

    private final ClimateServices climateServices;

    public ClimateDateAndLocationQuestionAndAnswer(ClimateServices climateServices){
        this.climateServices = climateServices;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("ClimateSpecific - DateAndLocation");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        Map<String, Object> parameters = messageEntity.getParameters();

        String currentDate = (String) parameters.get("date");
        String userDate = getDateFormatted(currentDate);

        Map<String, Object> location = (Map<String, Object>) parameters.get("location");
        String userCity = (String) location.get("city");

        return createAnswer(userDate, userCity);

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

    private String getDateFormatted(String currentDate){
        try {

            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = dateParser.parse(currentDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

            return dateFormatter.format(date);

        }catch (ParseException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
