package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.MessageEntity;
import chatbot.chatbot.interfaces.Question;

public class GreetingsQuestionsAndAnswers implements Question {

    @Override
    public boolean verifyIntent(MessageEntity messageEntity){
        String intent = messageEntity.getIntent();
        return intent.equals("Greetings");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity){
        return "Olá! Até o momento sou capaz de responder sobre o nome e a idade do meu desenvolvedor, " +
                "sobre o clima de onde ele mora e sobre o clima de qualquer cidade específica. Gostaria de " +
                "me testar?! Se sim, pergunte-me algo ou fale-me que gostaria de saber sobre o clima.";
    }
}