package chatbot.chatbot.messenger;

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
public class PostMessaging {
    private Map<String, String> sender = new HashMap<>();
    private PostMessage message;
}