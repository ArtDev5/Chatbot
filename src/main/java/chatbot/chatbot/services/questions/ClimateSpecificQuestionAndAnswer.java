package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.MessageEntity;
import chatbot.chatbot.interfaces.Question;

public class ClimateSpecificQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("ClimateSpecific");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        return "Me informe o dia e o local.";
    }
}
