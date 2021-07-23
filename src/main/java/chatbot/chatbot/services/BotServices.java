package chatbot.chatbot.services;

import chatbot.chatbot.dialogflow.DialogflowServices;
import chatbot.chatbot.dialogflow.DialogflowIntentAndEntities;
import chatbot.chatbot.dialogflow.IntentAndEntities;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.messenger.ReceiveMessage;
import chatbot.chatbot.messenger.ResponseEvent;
import chatbot.chatbot.services.questions.QuestionsAndAnswers;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class BotServices {

    private final RestTemplate restTemplate;
    private final DialogflowServices dialogflow;
    private final QuestionsAndAnswers questionsAndAnswers;

    public BotServices(RestTemplate restTemplate, DialogflowServices dialogflow, QuestionsAndAnswers questionsAndAnswers){
        this.restTemplate = restTemplate;
        this.dialogflow = dialogflow;
        this.questionsAndAnswers = questionsAndAnswers;
    }

    @Async
    public void answerUser(ReceiveMessage message, String responseUrl){
        String userId = message.getUserId();
        String userMessage = message.getUserMessage();

        String answer = getAnswer(userMessage);

        ResponseEvent response = new ResponseEvent(userId, answer);

        HttpEntity<ResponseEvent> entity = new HttpEntity<>(response);

        restTemplate.postForEntity(responseUrl, entity, String.class);
    }

    private String getAnswer(String userMessage){

        DialogflowIntentAndEntities dialogflowMessage = dialogflow.getIntentAndEntityFromDialogflow(userMessage);

        String intent = dialogflowMessage.getIntent();
        Map<String, Object> parameters = dialogflowMessage.getParameters();

        IntentAndEntities intentAndEntities = new IntentAndEntities(intent, parameters);

        for(Question values: questionsAndAnswers.getQuestionsWithAnswers()){
            if(values.verifyIntent(intentAndEntities)){
                return values.getAnswer(intentAndEntities);
            }
        }

        return "Desculpe-me, não entendi.";
    }
}
