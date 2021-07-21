package chatbot.chatbot.messenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiveMessage {
    private String userId;
    private String userMessage;

    public ReceiveMessage(ReceiveEvent event){
        event.getEntry().forEach( entry -> entry.getMessaging().forEach(messaging -> {
            userId = messaging.getSender().get("id");
            userMessage = messaging.getMessage().getText();
        }));
    }
}
