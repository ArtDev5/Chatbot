package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.IntentAndEntities;
import chatbot.chatbot.interfaces.Question;

public class ClimateSpecificQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(IntentAndEntities intentAndEntities) {
        return intentAndEntities.getIntent().equals("ClimateSpecific");
    }

    @Override
    public String getAnswer(IntentAndEntities intentAndEntities) {
        return "Me informe o dia e o local.";
    }
}
