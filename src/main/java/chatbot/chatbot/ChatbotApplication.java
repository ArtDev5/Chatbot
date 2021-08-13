package chatbot.chatbot;

import chatbot.chatbot.climate.ClimateServices;
import chatbot.chatbot.dialogflow.DialogflowServices;
import chatbot.chatbot.interfaces.Question;
import chatbot.chatbot.manager.ContextManagerImpl;
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
	public AgeIntentAndAnswer ageQuestionAndAnswer(ContextManagerImpl contextManagerImpl){
		return new AgeIntentAndAnswer(contextManagerImpl);
	}

	@Bean
	public NameIntentAndAnswer nameQuestionAndAnswer(ContextManagerImpl contextManagerImpl){
		return new NameIntentAndAnswer(contextManagerImpl);
	}

	@Bean
	public GreetingsIntentAndAnswer greetingsQuestionsAndAnswers(ContextManagerImpl contextManagerImpl){
		return new GreetingsIntentAndAnswer(contextManagerImpl);
	}
	@Bean
	public ClimateIntentAndAnswer climateQuestionAndAnswer(ClimateServices climateServices,
														   ContextManagerImpl contextManagerImpl){
		return new ClimateIntentAndAnswer(climateServices, contextManagerImpl);
	}

	@Bean
	public ClimateSpecificIntentAndAnswer climateSpecificQuestionAndAnswer(ContextManagerImpl contextManagerImpl){
		return new ClimateSpecificIntentAndAnswer(contextManagerImpl);
	}

	@Bean
	public ClimateFlowIntentAndAnswer climateDateQuestionAndAnswer(ContextManagerImpl contextManagerImpl,
                                                                   ClimateServices climateServices){
		return new ClimateFlowIntentAndAnswer(contextManagerImpl, climateServices);
	}

	@Bean
	public HelpIntentAndAnswer helpIntentAndAnswer(){
		return new HelpIntentAndAnswer();
	}

	@Bean
	public GetOutOfTheFlowIntentAndAnswer getOutOfTheFlowIntentAndAnswer(ContextManagerImpl contextManagerImpl){
		return new GetOutOfTheFlowIntentAndAnswer(contextManagerImpl);
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
	public ContextManagerImpl messageContextTest(){
		return new ContextManagerImpl();
	}


}
