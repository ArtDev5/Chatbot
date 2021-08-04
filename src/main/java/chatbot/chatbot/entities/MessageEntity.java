package chatbot.chatbot.dialogflow;

import java.util.Map;

public class MessageEntity {
    private String userMessage;
    private String userId;
    private String intent;
    private Map<String, String> entities;

    public MessageEntity(String userMessage, String userId, String intent, Map<String, String> entities) {
        this.userMessage = userMessage;
        this.userId = userId;
        this.intent = intent;
        this.entities = entities;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Map<String, String> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, String> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "userMessage='" + userMessage + '\'' +
                ", userId='" + userId + '\'' +
                ", intent='" + intent + '\'' +
                ", entities=" + entities +
                '}';
    }
}
