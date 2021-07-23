package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.IntentAndEntities;
import chatbot.chatbot.interfaces.Question;

public class NameQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(IntentAndEntities intentAndEntities){
        return intentAndEntities.getIntent().equals("Name");
    }

    @Override
    public String getAnswer(IntentAndEntities intentAndEntities) {
        return "Artur Silva.";
    }
}
