package chatbot.chatbot.services.questions;

import chatbot.chatbot.interfaces.Question;

import java.util.List;

public class QuestionsAndAnswers {

    private final List<Question> listQuestionsAndAnswers;

    public QuestionsAndAnswers(List<Question> listQuestionsAndAnswers){
        this.listQuestionsAndAnswers = listQuestionsAndAnswers;
    }

    public List<Question> getQuestionsWithAnswers(){

        return listQuestionsAndAnswers;
    }
}
