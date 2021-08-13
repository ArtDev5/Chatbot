package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.ContextManagerImpl;

public class NameIntentAndAnswer implements Question {

    private final ContextManagerImpl contextManagerImpl;

    public NameIntentAndAnswer(ContextManagerImpl contextManagerImpl){
        this.contextManagerImpl = contextManagerImpl;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();

        String userId = messageEntity.getUserId();
        String flag = contextManagerImpl.getContextData(userId, "flag");

        return intent.equals("Name") && flag.equals("");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        return "Artur Silva.";
    }
}
