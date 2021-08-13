package chatbot.chatbot.services.questions;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.climate.ResponseClimate;
import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.ContextManagerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ClimateFlowIntentAndAnswer implements Question {

    private final ContextManagerImpl contextManagerImpl;
    private final ClimateServices climateServices;

    public ClimateFlowIntentAndAnswer(ContextManagerImpl contextManagerImpl, ClimateServices climateServices){
        this.contextManagerImpl = contextManagerImpl;
        this.climateServices = climateServices;
    }

    @Override
    public boolean verifyIntent(MessageEntity messageEntity) {
        String userId = messageEntity.getUserId();
        String flag = contextManagerImpl.getContextData(userId, "flag");

        return flag.equals("climate");
    }

    @Override
    public String getAnswer(MessageEntity messageEntity) {

        Map<String, String> entities = messageEntity.getEntities();

        String userId = messageEntity.getUserId();
        String answer = "Houve um erro. Consultar suporte.";

        String userCity = entities.get("city");
        String userDate = entities.get("date");

        if(userCity != null){
            String cityInContext = contextManagerImpl.getContextData(userId, "city");

            if(!cityInContext.equals("")){
                answer = "Local já foi informado. Informe o dia.";
            }else{
                answer = "Salvo. Agora informe o dia.";
                contextManagerImpl.setContextData(userId, "city", userCity);
            }
        }

        if(userDate != null){
            String dateInContext = contextManagerImpl.getContextData(userId, "date");

            if(!dateInContext.equals("")){
                answer = "Dia já foi informado. Informe o local";
            }else{
                answer = "Salvo. Agora informe o local";
                String formattedDate = formattedDate = getFormattedDate(userDate);
                contextManagerImpl.setContextData(userId, "date", formattedDate);
            }
        }

        String date = contextManagerImpl.getContextData(userId, "date");
        String city = contextManagerImpl.getContextData(userId, "city");

        String verifiedAnswers = verifyAnswers(userId, date, city);

        if(verifiedAnswers.equals("")) {
            return answer;
        }
        return verifiedAnswers;
    }

    private String verifyAnswers(String userId, String userDate, String userCity){

        if(!userDate.equals("") && !userCity.equals("")){
            deleteDataFromUserContext(userId);
            return createAnswer(userDate, userCity);
        }
        return "";
    }

    private void deleteDataFromUserContext(String userId){
        contextManagerImpl.deleteContextData(userId, "date");
        contextManagerImpl.deleteContextData(userId, "city");
        contextManagerImpl.deleteContextData(userId, "flag");
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