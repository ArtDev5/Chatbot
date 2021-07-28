package chatbot.chatbot.dialogflow;

import java.util.Map;

public class QueryResult {
    private IntentDialogflow intent;
    private Map<String, Object> parameters;

    public IntentDialogflow getIntent() {
        return intent;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "intent=" + intent +
                ", parameters=" + parameters +
                '}';
    }
}
