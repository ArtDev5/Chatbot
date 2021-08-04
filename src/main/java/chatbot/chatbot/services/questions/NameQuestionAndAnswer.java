package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;

public class NameQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();
        return intent.equals("Name");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        return "Artur Silva.";
    }
}
