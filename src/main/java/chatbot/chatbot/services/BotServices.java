package chatbot.chatbot.services;

import chatbot.chatbot.dialogflow.DialogflowServices;
import chatbot.chatbot.entities.EntitiesData;
import chatbot.chatbot.entities.MessageEntity;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;
import chatbot.chatbot.messenger.ReceiveMessage;
import chatbot.chatbot.messenger.ResponseEvent;
import chatbot.chatbot.services.questions.IntentsAndAnswers;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class BotServices {

    private final RestTemplate restTemplate;
    private final DialogflowServices dialogflow;
    private final IntentsAndAnswers intentsAndAnswers;

    public BotServices(RestTemplate restTemplate, DialogflowServices dialogflow,
                       IntentsAndAnswers intentsAndAnswers){
        this.restTemplate = restTemplate;
        this.dialogflow = dialogflow;
        this.intentsAndAnswers = intentsAndAnswers;
    }

    @Async
    public void answerUser(ReceiveMessage message, String responseUrl){
        String userId = message.getUserId();
        String userMessage = message.getUserMessage();

        String answer = getAnswer(userMessage, userId);

        ResponseEvent response = new ResponseEvent(userId, answer);

        HttpEntity<ResponseEvent> entity = new HttpEntity<>(response);

        restTemplate.postForEntity(responseUrl, entity, String.class);
    }

    private String getAnswer(String userMessage, String userId){

        EntitiesData entitiesData = dialogflow.getDialogflowData(userMessage);

        String intent = entitiesData.getIntent();

        Map<String, String> entities = entitiesData.getEntities();

        MessageEntity messageEntity = new MessageEntity(userMessage, userId, intent, entities);

        for(Question values: intentsAndAnswers.getQuestionsWithAnswers()){
            if(values.verifyIntent(messageEntity)){
                return values.getAnswer(messageEntity);
            }
        }
        return "Desculpe-me, não entendi.";
    }
}
