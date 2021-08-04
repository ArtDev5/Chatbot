package chatbot.chatbot.interfaces;


import chatbot.chatbot.entities.MessageEntity;

public interface Question {

    boolean verifyIntent(MessageEntity messageEntity);
    String getAnswer(MessageEntity messageEntity);
}
