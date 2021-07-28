package chatbot.chatbot.dialogflow;

import java.util.Map;

public class MessageEntity {
    private String intent;
    private Map<String, Object> parameters;

    public MessageEntity(String intent, Map<String, Object> parameters) {
        this.intent = intent;
        this.parameters = parameters;
    }

    public String getIntent() {
        return intent;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "IntentAndEntities{" +
                "intent='" + intent + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
