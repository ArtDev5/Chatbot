package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.IntentAndEntities;
import chatbot.chatbot.interfaces.Question;

public class AgeQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(IntentAndEntities intentAndEntities){
        return intentAndEntities.getIntent().equals("Age");
    }

    @Override
    public String getAnswer(IntentAndEntities intentAndEntities){
        return "20 anos.";
    }
}
