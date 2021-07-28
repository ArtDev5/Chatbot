package chatbot.chatbot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseEventToDialogflow {
    @JsonProperty(value = "query_input")
    private TextDialogflow queryInput;

    public ResponseEventToDialogflow(TextDialogflow text){
        this.queryInput = text;
    }

    @Override
    public String toString() {
        return "ResponseEventToDialogflow{" +
                "queryInput=" + queryInput +
                '}';
    }
}
