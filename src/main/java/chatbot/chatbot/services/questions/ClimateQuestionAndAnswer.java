package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.Climate;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;
import org.springframework.stereotype.Component;

@Component
public class ClimateQuestionAndAnswer implements Question {

    private final Climate climate;

    public ClimateQuestionAndAnswer(Climate climate){
        this.climate = climate;
    }
    @Override
    public boolean verifyIntent(Message message){
        return message.getIntent().equals("Climate");
    }

    @Override
    public String getAnswer(Message message){
        ResponseClimate responseClimate = climate.getClimate();

        String weatherResult = "Na minha cidade (" + responseClimate.getCityName() + ") está " +
                "batendo " + responseClimate.getTemp() +"ºC com a condição '" + responseClimate.getCondition() + "', " +
                "além da temperatura máxima de " +responseClimate.getMax() +
                " e mínima de " + responseClimate.getMin();

        return weatherResult;
    }
}
