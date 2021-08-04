package chatbot.chatbot.services.questions;

import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ClimateDateQuestionAndAnswer implements Question {

    private final MessageContext messageContext;

    public ClimateDateQuestionAndAnswer(MessageContext messageContext){
        this.messageContext = messageContext;

    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();
        return intent.equals("ClimateSpecific - Date");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        Map<String, String> entities = messageEntity.getEntities();
        String date = entities.get("date");

        String formattedDate = getDateFormatted(date);

        String userId = messageEntity.getUserId();
        messageContext.setContextData(userId, "date", formattedDate);

        return "Salvo. Por favor, agora me informe o local.";
    }

    private String getDateFormatted(String currentDate){
        try {

            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = dateParser.parse(currentDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

            return dateFormatter.format(date);

        }catch (ParseException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
