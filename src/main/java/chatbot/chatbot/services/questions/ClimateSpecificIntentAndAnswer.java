package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

public class ClimateSpecificIntentAndAnswer implements Question {

    private final MessageContext messageContext;

    public ClimateSpecificIntentAndAnswer(MessageContext messageContext){
        this.messageContext = messageContext;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        String userId = messageEntity.getUserId();
        String flag = messageContext.getContextData(userId, "flag");

        return intent.equals("ClimateSpecific") && flag.equals("");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        String userId = messageEntity.getUserId();
        messageContext.setContextData(userId, "flag", "climate");
        return "Me informe o dia e o local.";
    }
}
