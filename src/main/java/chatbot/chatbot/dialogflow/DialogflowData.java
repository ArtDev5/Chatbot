package chatbot.chatbot.dialogflow;

import java.util.Map;

public class DialogflowData {
    private String intent;
    private Map<String, Object> parameters;

    public void convertDialogflowData(DialogflowContract dialogflowContract){
        QueryResult queryResult = dialogflowContract.getQueryResult();
        DialogflowIntent dialogflowIntent = queryResult.getIntent();
        intent = dialogflowIntent.getDisplayName();

        parameters = queryResult.getParameters();
    }

    public String getIntent() {
        return intent;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "DialogflowIntentAndEntities{" +
                "intent='" + intent + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
