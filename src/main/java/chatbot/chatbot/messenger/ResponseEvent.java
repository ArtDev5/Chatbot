package chatbot.chatbot.messenger;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class ResponseEvent {
    @JsonProperty("message_type")
    private String messageType;
    private Map<String, String> recipient = new HashMap<>();
    private Map<String, String> message = new HashMap<>();

    public ResponseEvent(String userId, String userText){
        this.messageType = "text";
        this.recipient.put("id", userId);
        this.message.put("text", userText);
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Map<String, String> getRecipient() {
        return recipient;
    }

    public void setRecipient(Map<String, String> recipient) {
        this.recipient = recipient;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseEvent{" +
                "messageType='" + messageType + '\'' +
                ", recipient=" + recipient +
                ", message=" + message +
                '}';
    }
}
