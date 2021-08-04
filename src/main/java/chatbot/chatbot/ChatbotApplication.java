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
	public AgeQuestionAndAnswer ageQuestionAndAnswer(){
		return new AgeQuestionAndAnswer();
	}

	@Bean
	public NameQuestionAndAnswer nameQuestionAndAnswer(){
		return new NameQuestionAndAnswer();
	}

	@Bean
	public GreetingsQuestionsAndAnswers greetingsQuestionsAndAnswers(){
		return new GreetingsQuestionsAndAnswers();
	}
	@Bean
	public ClimateQuestionAndAnswer climateQuestionAndAnswer(ClimateServices climateServices){
		return new ClimateQuestionAndAnswer(climateServices);
	}

	@Bean
	public ClimateSpecificQuestionAndAnswer climateSpecificQuestionAndAnswer(){
		return new ClimateSpecificQuestionAndAnswer();
	}

	@Bean
	public ClimateDateQuestionAndAnswer climateDateQuestionAndAnswer(MessageContext messageContext){
		return new ClimateDateQuestionAndAnswer(messageContext);
	}

	@Bean
	public ClimateLocationQuestionAndAnswer climateDateLocationQuestion(ClimateServices climateServices,
																		MessageContext messageContext){
		return new ClimateLocationQuestionAndAnswer(climateServices, messageContext);
	}

	@Bean
	public QuestionsAndAnswers questionsAndAnswers(AgeQuestionAndAnswer ageQuestionAndAnswer,
												   NameQuestionAndAnswer nameQuestionAndAnswer,
												   GreetingsQuestionsAndAnswers greetingsQuestionsAndAnswers,
												   ClimateQuestionAndAnswer climateQuestionAndAnswer,
												   ClimateSpecificQuestionAndAnswer climateSpecificQuestionAndAnswer,
												   ClimateDateQuestionAndAnswer climateDateQuestionAndAnswer,
												   ClimateLocationQuestionAndAnswer climateLocationQuestionAndAnswer){

		List<Question> answersAndQuestions = new ArrayList<>();

		answersAndQuestions.add(ageQuestionAndAnswer);
		answersAndQuestions.add(nameQuestionAndAnswer);
		answersAndQuestions.add(greetingsQuestionsAndAnswers);
		answersAndQuestions.add(climateQuestionAndAnswer);
		answersAndQuestions.add(climateSpecificQuestionAndAnswer);
		answersAndQuestions.add(climateDateQuestionAndAnswer);
		answersAndQuestions.add(climateLocationQuestionAndAnswer);

		return new QuestionsAndAnswers(answersAndQuestions);
	}

	@Bean
	public BotServices botServices(RestTemplate restTemplate, DialogflowServices dialogflowServices,
								   QuestionsAndAnswers questionsAndAnswers){
		return new BotServices(restTemplate, dialogflowServices, questionsAndAnswers);
	}

	@Bean
	public MessageContext messageContextTest(){
		return new MessageContext();
	}


}
