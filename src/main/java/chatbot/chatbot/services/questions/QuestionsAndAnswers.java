package chatbot.chatbot.services.questions;

import chatbot.chatbot.interfaces.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAndAnswers {

    private final AgeQuestionAndAnswer ageQuestionAndAnswer;
    private final NameQuestionAndAnswer nameQuestionAndAnswer;
    private final ClimateQuestionAndAnswer climateQuestionAndAnswer;
    private final ClimateSpecificQuestionAndAnswer climateSpecificQuestionAndAnswer;
    private final ClimateDateAndLocationQuestionAndAnswer climateDateAndLocationQuestionAndAnswer;

    public QuestionsAndAnswers(AgeQuestionAndAnswer ageQuestionAndAnswer,
                               NameQuestionAndAnswer nameQuestionAndAnswer,
                               ClimateQuestionAndAnswer climateQuestionAndAnswer,
                               ClimateSpecificQuestionAndAnswer climateSpecificQuestionAndAnswer,
                               ClimateDateAndLocationQuestionAndAnswer climateDateAndLocationQuestionAndAnswer){

        this.ageQuestionAndAnswer = ageQuestionAndAnswer;
        this.nameQuestionAndAnswer = nameQuestionAndAnswer;
        this.climateQuestionAndAnswer = climateQuestionAndAnswer;
        this.climateSpecificQuestionAndAnswer = climateSpecificQuestionAndAnswer;
        this.climateDateAndLocationQuestionAndAnswer = climateDateAndLocationQuestionAndAnswer;
    }

    public List<Question> getQuestionsWithAnswers(){

        List<Question> answersAndQuestions = new ArrayList<>();

        answersAndQuestions.add(ageQuestionAndAnswer);
        answersAndQuestions.add(nameQuestionAndAnswer);
        answersAndQuestions.add(climateQuestionAndAnswer);
        answersAndQuestions.add(climateSpecificQuestionAndAnswer);
        answersAndQuestions.add(climateDateAndLocationQuestionAndAnswer);

        return answersAndQuestions;
    }
}
