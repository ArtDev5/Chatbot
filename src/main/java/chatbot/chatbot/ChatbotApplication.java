package chatbot.chatbot;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.dialogflow.DialogflowServices;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.MessageContext;
import chatbot.chatbot.services.BotServices;
import chatbot.chatbot.services.questions.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public ClimateServices climateServices(RestTemplate restTemplate){
		return new ClimateServices(restTemplate);
	}

	@Bean
	public DialogflowServices dialogflowServices(RestTemplate restTemplate){
		return new DialogflowServices(restTemplate);
	}

	@Bean
	public AgeIntentAndAnswer ageQuestionAndAnswer(MessageContext messageContext){
		return new AgeIntentAndAnswer(messageContext);
	}

	@Bean
	public NameIntentAndAnswer nameQuestionAndAnswer(MessageContext messageContext){
		return new NameIntentAndAnswer(messageContext);
	}

	@Bean
	public GreetingsIntentAndAnswer greetingsQuestionsAndAnswers(MessageContext messageContext){
		return new GreetingsIntentAndAnswer(messageContext);
	}
	@Bean
	public ClimateIntentAndAnswer climateQuestionAndAnswer(ClimateServices climateServices,
														   MessageContext messageContext){
		return new ClimateIntentAndAnswer(climateServices, messageContext);
	}

	@Bean
	public ClimateSpecificIntentAndAnswer climateSpecificQuestionAndAnswer(MessageContext messageContext){
		return new ClimateSpecificIntentAndAnswer(messageContext);
	}

	@Bean
	public ClimateFlowIntentAndAnswer climateDateQuestionAndAnswer(MessageContext messageContext,
																   ClimateServices climateServices){
		return new ClimateFlowIntentAndAnswer(messageContext, climateServices);
	}

	@Bean
	public HelpIntentAndAnswer helpIntentAndAnswer(){
		return new HelpIntentAndAnswer();
	}

	@Bean
	public GetOutOfTheFlowIntentAndAnswer getOutOfTheFlowIntentAndAnswer(MessageContext messageContext){
		return new GetOutOfTheFlowIntentAndAnswer(messageContext);
	}

	@Bean
	public IntentsAndAnswers questionsAndAnswers(AgeIntentAndAnswer ageIntentAndAnswer,
												 NameIntentAndAnswer nameIntentAndAnswer,
												 GreetingsIntentAndAnswer greetingsIntentAndAnswer,
												 ClimateIntentAndAnswer climateIntentAndAnswer,
												 ClimateSpecificIntentAndAnswer climateSpecificIntentAndAnswer,
												 ClimateFlowIntentAndAnswer climateFlowIntentAndAnswer,
												 GetOutOfTheFlowIntentAndAnswer getOutOfTheFlowIntentAndAnswer,
												 HelpIntentAndAnswer helpIntentAndAnswer){

		List<Question> answersAndQuestions = new ArrayList<>();

		answersAndQuestions.add(getOutOfTheFlowIntentAndAnswer);
		answersAndQuestions.add(helpIntentAndAnswer);
		answersAndQuestions.add(ageIntentAndAnswer);
		answersAndQuestions.add(nameIntentAndAnswer);
		answersAndQuestions.add(greetingsIntentAndAnswer);
		answersAndQuestions.add(climateIntentAndAnswer);
		answersAndQuestions.add(climateSpecificIntentAndAnswer);
		answersAndQuestions.add(climateFlowIntentAndAnswer);

		return new IntentsAndAnswers(answersAndQuestions);
	}

	@Bean
	public BotServices botServices(RestTemplate restTemplate, DialogflowServices dialogflowServices,
								   IntentsAndAnswers intentsAndAnswers){
		return new BotServices(restTemplate, dialogflowServices, intentsAndAnswers);
	}

	@Bean
	public MessageContext messageContextTest(){
		return new MessageContext();
	}


}
