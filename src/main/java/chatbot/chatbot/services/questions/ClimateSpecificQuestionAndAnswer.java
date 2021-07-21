package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;
import org.springframework.stereotype.Component;

@Component
public class ClimateSpecificQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(Message message) {
        return message.getIntent().equals("ClimateSpecific");
    }

    @Override
    public String getAnswer(Message message) {
        return "Me informe a data e o local.\nExemplo: 14/08/2021 - São Paulo";
    }
}
