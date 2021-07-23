package chatbot.chatbot.services.questions;

import chatbot.chatbot.dialogflow.IntentAndEntities;
import chatbot.chatbot.interfaces.Question;

public class GreetingsQuestionsAndAnswers implements Question {

    @Override
    public boolean verifyIntent(IntentAndEntities intentAndEntities){
        return intentAndEntities.getIntent().equals("Greetings");
    }

    @Override
    public String getAnswer(IntentAndEntities intentAndEntities){
        return "Olá! Até o momento sou capaz de responder sobre o nome e a idade do meu desenvolvedor, " +
                "sobre o clima de onde ele mora e sobre o clima de qualquer cidade específica. Gostaria de " +
                "me testar?! Se sim, pergunte-me algo ou fale-me que gostaria de saber sobre o clima.";
    }
}