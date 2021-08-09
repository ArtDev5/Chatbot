package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

public class GetOutOfTheFlowIntentAndAnswer implements Question {

    private final MessageContext messageContext;

    public GetOutOfTheFlowIntentAndAnswer(MessageContext messageContext){
        this.messageContext = messageContext;
    }
    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("Get out of the flow");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        String userId = messageEntity.getUserId();

        String flag = messageContext.getContextData(userId, "flag");
        if(!flag.equals("")){
            messageContext.clearContext(userId);
            return "Ok, fluxo quebrado.";
        }else{
            return "Você precisa estar em um fluxo para poder utilizar essa função.";
        }
    }
}
