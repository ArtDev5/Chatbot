package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

public class ClimateQuestionAndAnswer implements Question {

    private final ClimateServices climateServices;
    private final MessageContext messageContext;

    public ClimateQuestionAndAnswer(ClimateServices climateServices, MessageContext messageContext){
        this.climateServices = climateServices;
        this.messageContext = messageContext;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();

        String userId = messageEntity.getUserId();
        String flag = messageContext.getContextData(userId, "flag");

        return intent.equals("Climate") && flag.equals("");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity){
        ResponseClimate responseClimate = climateServices.getClimate();

        String answer = "Na minha cidade (" + responseClimate.getCityName() +
                ") está batendo " + responseClimate.getTemp() +
                "ºC com a condição '" + responseClimate.getCondition() +
                "' além da temperatura máxima de " + responseClimate.getMax() +
                " e mínima de " + responseClimate.getMin();

        return answer;
    }
}
