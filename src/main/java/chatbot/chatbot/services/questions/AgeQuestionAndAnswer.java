package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;
import org.springframework.stereotype.Component;

@Component
public class AgeQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(Message message){
        return message.getIntent().equals("Age");
    }

    @Override
    public String getAnswer(Message message){
        return "20 anos.";
    }
}
