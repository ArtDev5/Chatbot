package chatbot.chatbot.dialogflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DialogflowMessage {
    private String intent;
    private String city;
    private Date date;

    public DialogflowMessage(ReceiveMessageFromDialogflow event) {
        intent = event.getQueryResult().getIntent().getDisplayName();
        if(intent.equals("ClimateSpecific - DateAndLocation")){
            date = event.getQueryResult().getParameters().getDate();
            city = event.getQueryResult().getParameters().getLocation().getCity();
        }
    }
}
