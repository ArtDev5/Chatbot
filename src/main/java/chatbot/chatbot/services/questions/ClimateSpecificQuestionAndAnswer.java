package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;

public class ClimateSpecificQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(Message message) {
        return message.getIntent().equals("ClimateSpecific");
    }

    @Override
    public String getAnswer(Message message) {
        return "Me informe o dia e o local.";
    }
}
