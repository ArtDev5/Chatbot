package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.dialogflow.IntentAndEntities;
import chatbot.chatbot.interfaces.Question;

public class ClimateQuestionAndAnswer implements Question {

    private final ClimateServices climateServices;

    public ClimateQuestionAndAnswer(ClimateServices climateServices){
        this.climateServices = climateServices;
    }
    @Override
    public boolean verifyIntent(IntentAndEntities intentAndEntities){
        return intentAndEntities.getIntent().equals("Climate");
    }

    @Override
    public String getAnswer(IntentAndEntities intentAndEntities){
        ResponseClimate responseClimate = climateServices.getClimate();

        String weatherResult = "Na minha cidade (" + responseClimate.getCityName() + ") está " +
                "batendo " + responseClimate.getTemp() +"ºC com a condição '" + responseClimate.getCondition() + "', " +
                "além da temperatura máxima de " +responseClimate.getMax() +
                " e mínima de " + responseClimate.getMin();

        return weatherResult;
    }
}
