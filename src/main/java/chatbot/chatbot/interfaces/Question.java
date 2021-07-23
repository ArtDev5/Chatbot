package chatbot.chatbot.interfaces;


import chatbot.chatbot.dialogflow.IntentAndEntities;

public interface Question {

    boolean verifyIntent(IntentAndEntities intentAndEntities);
    String getAnswer(IntentAndEntities intentAndEntities);
}
