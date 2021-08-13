package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.ContextManagerImpl;

public class GetOutOfTheFlowIntentAndAnswer implements Question {

    private final ContextManagerImpl contextManagerImpl;

    public GetOutOfTheFlowIntentAndAnswer(ContextManagerImpl contextManagerImpl){
        this.contextManagerImpl = contextManagerImpl;
    }
    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("Get out of the flow");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        String userId = messageEntity.getUserId();

        String flag = contextManagerImpl.getContextData(userId, "flag");
        if(!flag.equals("")){
            contextManagerImpl.clearContext(userId);
            return "Ok, fluxo quebrado.";
        }else{
            return "Você precisa estar em um fluxo para poder utilizar essa função.";
        }
    }
}
