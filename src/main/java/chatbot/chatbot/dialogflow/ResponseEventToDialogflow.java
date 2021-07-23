package chatbot.chatbot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ResponseEventToDialogflow {
    @JsonProperty(value = "query_input")
    private TextDialogflow queryInput;

    public ResponseEventToDialogflow(TextDialogflow text){
        this.queryInput = text;
    }
}
