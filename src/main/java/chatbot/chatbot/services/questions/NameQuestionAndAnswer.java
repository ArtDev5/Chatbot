package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;
import org.springframework.stereotype.Component;

@Component
public class NameQuestionAndAnswer implements Question {

    @Override
    public boolean verifyIntent(Message message){
        return message.getIntent().equals("Name");
    }

    @Override
    public String getAnswer(Message message) {
        return "Artur Silva.";
    }
}