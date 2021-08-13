package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.ContextManagerImpl;

public class ClimateSpecificIntentAndAnswer implements Question {

    private final ContextManagerImpl contextManagerImpl;

    public ClimateSpecificIntentAndAnswer(ContextManagerImpl contextManagerImpl){
        this.contextManagerImpl = contextManagerImpl;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        String userId = messageEntity.getUserId();
        String flag = contextManagerImpl.getContextData(userId, "flag");

        return intent.equals("ClimateSpecific") && flag.equals("");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        String userId = messageEntity.getUserId();
        contextManagerImpl.setContextData(userId, "flag", "climate");
        return "Me informe o dia e o local.";
    }
}
