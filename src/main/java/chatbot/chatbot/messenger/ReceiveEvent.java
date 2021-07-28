package chatbot.chatbot.messenger;

import java.util.ArrayList;
import java.util.List;


public class ReceiveEvent {
    private String object;
    private List<PostEntry> entry = new ArrayList<>();

    public String getObject() {
        return object;
    }

    public List<PostEntry> getEntry() {
        return entry;
    }

    @Override
    public String toString() {
        return "ReceiveEvent{" +
                "object='" + object + '\'' +
                ", entry=" + entry +
                '}';
    }
}
