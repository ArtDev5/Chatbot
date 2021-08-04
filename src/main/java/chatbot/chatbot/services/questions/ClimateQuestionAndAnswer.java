package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;

public class ClimateQuestionAndAnswer implements Question {

    private final ClimateServices climateServices;

    public ClimateQuestionAndAnswer(ClimateServices climateServices){
        this.climateServices = climateServices;
    }
    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();
        return intent.equals("Climate");
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
