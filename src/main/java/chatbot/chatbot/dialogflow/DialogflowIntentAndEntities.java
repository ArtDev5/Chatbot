package chatbot.chatbot.dialogflow;

import java.util.Map;

public class DialogflowIntentAndEntities {
    private String intent;
    private Map<String, Object> parameters;

    public DialogflowIntentAndEntities(DialogflowContract dialogflowContract){
        intent = dialogflowContract.getQueryResult().getIntent().getDisplayName();
        parameters = dialogflowContract.getQueryResult().getParameters();
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
