package chatbot.chatbot.services;

import chatbot.chatbot.dialogflow.Dialogflow;
import chatbot.chatbot.dialogflow.DialogflowMessage;
import chatbot.chatbot.dialogflow.Message;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.messenger.ReceiveMessage;
import chatbot.chatbot.messenger.ResponseEvent;
import chatbot.chatbot.services.questions.QuestionsAndAnswers;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotServices {

    private final RestTemplate restTemplate;
    private final Dialogflow dialogflow;
    private final QuestionsAndAnswers questionsAndAnswers;

    public BotServices(RestTemplate restTemplate, Dialogflow dialogflow, QuestionsAndAnswers questionsAndAnswers){
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

        DialogflowMessage dialogflowMessage = dialogflow.getIntentAndEntityFromDialogflow(userMessage);
        Message message = new Message(dialogflowMessage);

        for(Question values: questionsAndAnswers.getQuestionsWithAnswers()){
            if(values.verifyIntent(message)){
                return values.getAnswer(message);
            }
        }

        return "Desculpe-me, não entendi.";
    }
}
