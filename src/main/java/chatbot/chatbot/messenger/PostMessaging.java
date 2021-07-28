package chatbot.chatbot.messenger;

import java.util.HashMap;
import java.util.Map;

public class PostMessaging {
    private Map<String, String> sender = new HashMap<>();
    private PostMessage message;

    public Map<String, String> getSender() {
        return sender;
    }

    public void setSender(Map<String, String> sender) {
        this.sender = sender;
    }

    public PostMessage getMessage() {
        return message;
    }

    public void setMessage(PostMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PostMessaging{" +
                "sender=" + sender +
                ", message=" + message +
                '}';
    }
}