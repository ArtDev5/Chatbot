package chatbot.chatbot.messenger;

import java.util.ArrayList;
import java.util.List;


public class PostEntry {
    private List<PostMessaging> messaging = new ArrayList<>();

    public List<PostMessaging> getMessaging() {
        return messaging;
    }

    @Override
    public String toString() {
        return "PostEntry{" +
                "messaging=" + messaging +
                '}';
    }
}