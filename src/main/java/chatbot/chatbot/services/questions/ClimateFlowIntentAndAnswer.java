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

public class ClimateFlowIntentAndAnswer implements Question {

    private final MessageContext messageContext;
    private final ClimateServices climateServices;

    public ClimateFlowIntentAndAnswer(MessageContext messageContext, ClimateServices climateServices){
        this.messageContext = messageContext;
        this.climateServices = climateServices;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String intent = messageEntity.getIntent();

        String userId = messageEntity.getUserId();
        String flag = messageContext.getContextData(userId, "flag");

        return flag.equals("climate") && intent.equals("ClimateSpecific - Date") ||
                flag.equals("climate") && intent.equals("ClimateSpecific - Location") ||
                flag.equals("climate") && intent.equals("ClimateSpecific - DateAndLocation");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {

        Map<String, String> entities = messageEntity.getEntities();
        String intent = messageEntity.getIntent();

        String userId = messageEntity.getUserId();
        String answer = "Erro no flow do climate";

        if(intent.equals("ClimateSpecific - DateAndLocation")){
            String userCity = entities.get("city");
            String userDate = entities.get("date");
            String formattedDate = getFormattedDate(userDate);

            messageContext.setContextData(userId, "date", formattedDate);
            messageContext.setContextData(userId, "city", userCity);
            answer = "Ok";
        }

        if(intent.equals("ClimateSpecific - Date")){
            String date = entities.get("date");
            String formattedDate = getFormattedDate(date);

            String dateInContext = messageContext.getContextData(userId, "date");

            if(!dateInContext.equals("")){
                answer = "O dia já foi informado, informe o local.";
            }else{
                messageContext.setContextData(userId, "date", formattedDate);
                answer = "Salvo. Por favor, agora me informe o local.";
            }
        }

        if (intent.equals("ClimateSpecific - Location")) {
            String city = entities.get("city");

            String cityInContext = messageContext.getContextData(userId, "city");

            if(!cityInContext.equals("")){
                answer = "O local já foi informado, informe o dia.";
            }else{
                messageContext.setContextData(userId, "city", city);
                answer = "Salvo. Por favor, agora me informe o dia.";
            }
        }

        String verifiedAnswers = verifyAnswers(userId);

        if (verifiedAnswers.equals("")) {
            return answer;
        }
        return verifiedAnswers;
    }

    private String verifyAnswers(String userId){
        String userDate = messageContext.getContextData(userId, "date");
        String userCity = messageContext.getContextData(userId, "city");

        if(!userDate.equals("") && !userCity.equals("")){
            messageContext.deleteContextData(userId, "date");
            messageContext.deleteContextData(userId, "city");
            messageContext.deleteContextData(userId, "flag");
            return createAnswer(userDate, userCity);
        }
        return "";
    }

    private String getFormattedDate(String currentDate){
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