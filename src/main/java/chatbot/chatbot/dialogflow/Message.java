package chatbot.chatbot.dialogflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private String userMessage;
    private String intent;

    public Message(DialogflowMessage dialogflowMessage) {
        userMessage = dialogflowMessage.getUserMessage();
        intent = dialogflowMessage.getIntent();
    }
}
