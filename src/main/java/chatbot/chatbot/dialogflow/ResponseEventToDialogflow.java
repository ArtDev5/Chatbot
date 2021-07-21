package chatbot.chatbot.dialogflow;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ResponseEventToDialogflow {

    private TextDialogflow query_input;

    public ResponseEventToDialogflow(TextDialogflow text){
        this.query_input = text;
    }
}
