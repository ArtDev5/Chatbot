package chatbot.chatbot.messenger;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ResponseEvent {
    @JsonProperty("message_type")
    private String messageType;
    private Map<String, String> recipient = new HashMap<>();
    private Map<String, String> message = new HashMap<>();

    public ResponseEvent(String userId, String userText){
        this.messageType = "text";
        this.recipient.put("id", userId);
        this.message.put("text", userText);
    }
}
