package chatbot.chatbot.dialogflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DialogflowIntentAndEntities {
    private String intent;
    private Map<String, Object> parameters = new HashMap<>();

    public DialogflowIntentAndEntities(DialogflowContract dialogflowContract){
        intent = dialogflowContract.getQueryResult().getIntent().getDisplayName();
        parameters = dialogflowContract.getQueryResult().getParameters();
    }

}
