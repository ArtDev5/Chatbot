package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

import java.util.Map;

public class ClimateLocationQuestionAndAnswer implements Question {

    private final ClimateServices climateServices;
    private final MessageContext messageContext;

    public ClimateLocationQuestionAndAnswer(ClimateServices climateServices,
                                            MessageContext messageContext){
        this.climateServices = climateServices;
        this.messageContext = messageContext;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("ClimateSpecific - Date - Location");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {

        Map<String, String> entities = messageEntity.getEntities();

        String userCity = entities.get("city");

        String userId = messageEntity.getUserId();
        String userDate = messageContext.getContextData(userId, "date");

        return createAnswer(userDate, userCity);

    }

    private String createAnswer(String userDate, String userCity){

        ResponseClimate responseClimate = climateServices.getClimate(userDate, userCity);

        if (responseClimate.getMax() == 0 && responseClimate.getMin() == 0) {
            return "Informe uma data dentro do limite, sendo do dia atual até 10 dias.";
        }

        String answer = "No dia " + userDate +
                " está/estará batendo " + responseClimate.getTemp() +
                "ºC na cidade " + responseClimate.getCityName() +
                " com a condição '" + responseClimate.getCondition() +
                "', além da temperatura máxima de " + responseClimate.getMax() +
                " e mínima de " + responseClimate.getMin();

        return answer;
    }


}
