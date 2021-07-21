package chatbot.chatbot.dialogflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiveMessageFromDialogflow {
    private String responseId;
    private QueryResult queryResult;
}
