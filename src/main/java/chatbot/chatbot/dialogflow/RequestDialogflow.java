package chatbot.chatbot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestDialogflow {
    @JsonProperty("query_input")
    private String queryInput;
    private TextDialogflow text;
}
