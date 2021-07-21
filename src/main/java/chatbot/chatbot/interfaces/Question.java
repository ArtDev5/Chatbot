package chatbot.chatbot.interfaces;


import chatbot.chatbot.dialogflow.Message;

public interface Question {

    boolean verifyIntent(Message message);
    String getAnswer(Message message);
}
