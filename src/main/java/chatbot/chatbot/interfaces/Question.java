package chatbot.chatbot.interfaces;


import chatbot.chatbot.dialogflow.MessageEntity;

public interface Question {

    boolean verifyIntent(MessageEntity messageEntity);
    String getAnswer(MessageEntity messageEntity);
}
