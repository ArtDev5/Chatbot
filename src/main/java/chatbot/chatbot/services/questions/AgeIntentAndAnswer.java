package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

public class AgeQuestionAndAnswer implements Question {

    private final MessageContext messageContext;

    public AgeQuestionAndAnswer(MessageContext messageContext){
        this.messageContext = messageContext;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();

        String userId = messageEntity.getUserId();
        String flag = messageContext.getContextData(userId, "flag");

        return intent.equals("Age") && flag.equals("");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity){
        return "20 anos.";
    }
}
