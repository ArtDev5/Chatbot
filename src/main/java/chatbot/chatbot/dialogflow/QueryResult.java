package chatbot.chatbot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class QueryResult {
    @JsonProperty(value = "queryText")
    private String userMessage;
    @JsonProperty(value = "fulfillmentText")
    private String dialogflowAnswer;
    private DialogflowIntent intent;
    private Map<String, Object> parameters;

    public String getUserMessage() {
        return userMessage;
    }

    public String getDialogflowAnswer() {
        return dialogflowAnswer;
    }

    public DialogflowIntent getIntent() {
        return intent;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "userMessage='" + userMessage + '\'' +
                ", dialogflowAnswer='" + dialogflowAnswer + '\'' +
                ", intent=" + intent +
                ", parameters=" + parameters +
                '}';
    }
}