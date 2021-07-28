package chatbot.chatbot.dialogflow;

public class DialogflowContract {
    private QueryResult queryResult;

    public QueryResult getQueryResult() {
        return queryResult;
    }

    @Override
    public String toString() {
        return "DialogflowContract{" +
                "queryResult=" + queryResult +
                '}';
    }
}
