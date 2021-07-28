package chatbot.chatbot.messenger;

public class ReceiveMessage {
    private String userId;
    private String userMessage;

    public ReceiveMessage(ReceiveEvent event){
        event.getEntry().forEach( entry -> entry.getMessaging().forEach(messaging -> {
            userId = messaging.getSender().get("id");
            userMessage = messaging.getMessage().getText();
        }));
    }

    public String getUserId() {
        return userId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    @Override
    public String toString() {
        return "ReceiveMessage{" +
                "userId='" + userId + '\'' +
                ", userMessage='" + userMessage + '\'' +
                '}';
    }
}
