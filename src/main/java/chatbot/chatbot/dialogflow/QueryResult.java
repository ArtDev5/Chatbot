package chatbot.chatbot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class QueryResult {
    @JsonProperty(value = "queryText")
    private String userMessage;
    @JsonProperty(value = "fulfillmentText")
    private String dialogflowAnswer;
    private DialogflowIntent intent;
    @JsonProperty(value = "parameters")
    private Map<String, Object> entities;

    public String getUserMessage() {
        return userMessage;
    }

    public String getDialogflowAnswer() {
        return dialogflowAnswer;
    }

    public DialogflowIntent getIntent() {
        return intent;
    }

    public Map<String, Object> getEntities() {
        return entities;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "userMessage='" + userMessage + '\'' +
                ", dialogflowAnswer='" + dialogflowAnswer + '\'' +
                ", intent=" + intent +
                ", entities=" + entities +
                '}';
    }
}