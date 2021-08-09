package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

public class GreetingsQuestionsAndAnswers implements Question {

    private final MessageContext messageContext;

    public GreetingsQuestionsAndAnswers(MessageContext messageContext){
        this.messageContext = messageContext;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();

        String userId = messageEntity.getUserId();
        String flag = messageContext.getContextData(userId, "flag");

        return intent.equals("Greetings") && flag.equals("");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity){
        return "Olá! Chamo-me TurBot e tenho quase dois meses de vida. Caso não esteja familiarizado comigo, envie " +
                "'help' para saber sobre o que posso fazer";
    }
}