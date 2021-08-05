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

        boolean result = intent.equals("ClimateSpecific - Date - Location") ||
                intent.equals("ClimateSpecific - Location");

        return result;
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {

        String intent = messageEntity.getIntent();

        Map<String, String> entities = messageEntity.getEntities();
        String userCity = entities.get("city");

        if(intent.equals("ClimateSpecific - Location")){
            return getAnswerToTheLocationAndDateStream(messageEntity, userCity);
        }

        String answerToReturn  = getAnswerToTheDateAndLocationStream(messageEntity, userCity);
        return answerToReturn;

    }

    private String getAnswerToTheLocationAndDateStream(MessageEntity messageEntity, String userCity){

        String userId = messageEntity.getUserId();
        messageContext.setContextData(userId, "city", userCity);

        String answer = "Salvo. Por favor, agora me informe o dia.";
        return answer;
    }

    private String getAnswerToTheDateAndLocationStream(MessageEntity messageEntity, String userCity){
        String userId = messageEntity.getUserId();
        String userDate = messageContext.getContextData(userId, "date");

        String answer = createAnswer(userDate, userCity);
        return answer;
    }

    private String createAnswer(String userDate, String userCity){

        ResponseClimate responseClimate = climateServices.getClimate(userDate, userCity);

        if (responseClimate.getMax() == 0 && responseClimate.getMin() == 0) {
            return "Informe uma data dentro do limite, sendo do dia atual até 10 dias.";
        }

        String answerFormat = "No dia " + userDate +
                " está/estará batendo " + responseClimate.getTemp() +
                "ºC na cidade " + responseClimate.getCityName() +
                " com a condição '" + responseClimate.getCondition() +
                "', além da temperatura máxima de " + responseClimate.getMax() +
                " e mínima de " + responseClimate.getMin();

        return answerFormat;
    }


}
