package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ClimateDateQuestionAndAnswer implements Question {

    private final MessageContext messageContext;
    private final ClimateServices climateServices;

    public ClimateDateQuestionAndAnswer(MessageContext messageContext, ClimateServices climateServices){
        this.messageContext = messageContext;
        this.climateServices = climateServices;

    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();

        boolean result = intent.equals("ClimateSpecific - Date") ||
                intent.equals("ClimateSpecific - Location - Date");

        return result;
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();

        Map<String, String> entities = messageEntity.getEntities();
        String date = entities.get("date");

        String formattedDate = getDateFormatted(date);

        if(intent.equals("ClimateSpecific - Date")){
            return getAnswerToTheDateAndLocationStream(messageEntity, formattedDate);
        }

        String answerToReturn = getAnswerToTheLocationAndDateStream(messageEntity, formattedDate);
        return answerToReturn;
    }

    private String getAnswerToTheDateAndLocationStream(MessageEntity messageEntity, String userDate){

        String userId = messageEntity.getUserId();
        messageContext.setContextData(userId, "date", userDate);

        String answer = "Salvo. Por favor, agora me informe o local.";
        return answer;
    }

    private String getAnswerToTheLocationAndDateStream(MessageEntity messageEntity, String userDate){
        String userId = messageEntity.getUserId();
        String city = messageContext.getContextData(userId, "city");

        String answer = createAnswer(userDate, city);
        return answer;
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

    private String createAnswer(String userDate, String userCity){

        ResponseClimate responseClimate = climateServices.getClimate(userDate, userCity);

        if (responseClimate.getMax() == 0 && responseClimate.getMin() == 0) {
            return "Informe uma data dentro do limite, sendo do dia atual até 10 dias.";
        }

        String answerFormat = "No dia " + userDate +
                " está/estará batendo " + responseClimate.getTemp() +
                "ºC na cidade " + responseClimate.getCityName() +
                " com a condição '" + responseClimate.getCondition() +
                "', além da temperatura máxima de " + responseClimate.getMax() +
                " e mínima de " + responseClimate.getMin();

        return answerFormat;
    }


}
