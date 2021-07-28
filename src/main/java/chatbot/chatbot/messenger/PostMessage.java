package chatbot.chatbot.messenger;

public class PostMessage {
    private String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "PostMessage{" +
                "text='" + text + '\'' +
                '}';
    }
}
