package chatbot.chatbot.dialogflow;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Message {
    private String intent;
    private String city;
    private Date date;

    public Message(String intent, String city, Date date) {
        this.intent = intent;
        this.city = city;
        this.date = date;
    }
}
