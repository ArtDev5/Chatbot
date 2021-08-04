package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.dialogflow.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;
import chatbot.chatbot.manager.MessageContextTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ClimateDateAndLocationQuestionAndAnswer implements Question {

    private final ClimateServices climateServices;
    private final MessageContext messageContext;
    private final MessageContextTest messageContextTest;

    public ClimateDateAndLocationQuestionAndAnswer(ClimateServices climateServices,
                                                   MessageContext messageContext,
                                                   MessageContextTest messageContextTest){
        this.climateServices = climateServices;
        this.messageContext = messageContext;
        this.messageContextTest = messageContextTest;
    }



    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("ClimateSpecific - DateAndLocation");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        Map<String, Object> parameters = messageEntity.getParameters();
        Map<String, Object> location = (Map<String, Object>) parameters.get("location");

        String userCity = (String) location.get("city");
        String userDate = messageContext.getDate();

        String userId = messageEntity.getUserId();
        String dateTest = messageContextTest.getContextData(userId, "date");

        System.out.println(dateTest + " - " + userCity);

        return createAnswer(userDate, userCity);

    }

    private String createAnswer(String userDate, String userCity){

        ResponseClimate responseClimate = climateServices.getClimate(userDate, userCity);

        if (responseClimate.getMax() == 0 && responseClimate.getMin() == 0) {
            return "Informe uma data dentro do limite, sendo do dia atual até 10 dias.";
        }
        StringBuilder builder = new StringBuilder();

        builder.append("No dia ").append(userDate);
        builder.append(" está/estará batendo ").append(responseClimate.getTemp());
        builder.append("ºC na cidade ").append(responseClimate.getCityName());
        builder.append(" com a condição '").append(responseClimate.getCondition());
        builder.append("', além da temperatura máxima de ").append(responseClimate.getMax());
        builder.append(" e mínima de ").append(responseClimate.getMin());

        return builder.toString();
    }


}
