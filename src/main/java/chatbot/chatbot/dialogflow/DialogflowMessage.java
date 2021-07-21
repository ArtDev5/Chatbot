package chatbot.chatbot.dialogflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DialogflowMessage {
    private String responseId;
    private String userMessage;
    private String dialogflowAnswer;
    private String intent;

    public DialogflowMessage(ReceiveMessageFromDialogflow event) {
        responseId = event.getResponseId();
        userMessage = event.getQueryResult().getQueryText();
        dialogflowAnswer = event.getQueryResult().getFulfillmentText();
        intent = event.getQueryResult().getIntent().getDisplayName();
    }
}
