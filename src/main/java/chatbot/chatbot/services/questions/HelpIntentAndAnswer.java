package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;

public class HelpIntentAndAnswer implements Question {
    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("Help");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        return "Até o momento sou capaz de responder sobre o nome e a idade do meu desenvolvedor, " +
                "sobre o clima de onde ele mora e sobre o clima de qualquer cidade específica." +
                "\nObs: Caso esteja em um fluxo e deseje sair, basta informar que deseja sair.";
    }
}
