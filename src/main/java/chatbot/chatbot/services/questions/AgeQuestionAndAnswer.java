package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.MessageEntity;
import chatbot.chatbot.interfaces.Question;

public class AgeQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();
        return intent.equals("Age");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity){
        return "20 anos.";
    }
}
