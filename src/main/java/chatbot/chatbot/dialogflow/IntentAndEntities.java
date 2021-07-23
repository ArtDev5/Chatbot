package chatbot.chatbot.dialogflow;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@NoArgsConstructor
@ToString
public class IntentAndEntities {
    private String intent;
    private Map<String, Object> parameters;

    public IntentAndEntities(String intent, Map<String, Object> parameters) {
        this.intent = intent;
        this.parameters = parameters;
    }
}
